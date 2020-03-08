package com.just.usercenter.rocketmq;

import com.just.usercenter.dao.user.BonusEventLogMapper;
import com.just.usercenter.dao.user.UserMapper;
import com.just.usercenter.domain.dto.messaging.UserAddBonusMsgDTO;
import com.just.usercenter.domain.entity.user.BonusEventLog;
import com.just.usercenter.domain.entity.user.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
@RocketMQMessageListener(consumerGroup = "consumer-group",topic = "add-bonus")
public class AddBonusListener implements RocketMQListener<UserAddBonusMsgDTO> {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BonusEventLogMapper bonusEventLogMapper;

    @Override
    public void onMessage(UserAddBonusMsgDTO message) {
        //当收到信息的时候，执行的业务
        //1 为用户加积分
        Integer userId = message.getUserId();
        Integer bonus = message.getBonus();
        User user = userMapper.selectByPrimaryKey(userId);
        user.setBonus(user.getBonus()+message.getBonus());
        userMapper.updateByPrimaryKeySelective(user);
        //2 记录日志到bonus_event_log
        bonusEventLogMapper.insert(
                BonusEventLog.builder()
                        .userId(userId)
                        .value(bonus)
                        .event("CONTRIBUTE")
                        .createTime(new Date())
                        .description("投稿加积分..")
                        .build()
        );
        log.info("积分添加完毕...");
    }
}
