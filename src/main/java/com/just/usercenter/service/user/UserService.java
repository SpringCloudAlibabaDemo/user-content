package com.just.usercenter.service.user;

import com.just.usercenter.dao.user.BonusEventLogMapper;
import com.just.usercenter.dao.user.UserMapper;
import com.just.usercenter.domain.dto.messaging.UserAddBonusMsgDTO;
import com.just.usercenter.domain.entity.user.BonusEventLog;
import com.just.usercenter.domain.entity.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author yanghao
 * @date 2020/1/19 13:36
 */

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BonusEventLogMapper bonusEventLogMapper;

    public User findById(Integer id){

        return userMapper.selectByPrimaryKey(id);

    }

    @Transactional(rollbackFor = Exception.class)
    public void addBonus(UserAddBonusMsgDTO msgDTO){
        //当收到信息的时候，执行的业务
        //1 为用户加积分
        Integer userId = msgDTO.getUserId();
        Integer bonus = msgDTO.getBonus();
        User user = userMapper.selectByPrimaryKey(userId);
        user.setBonus(user.getBonus()+msgDTO.getBonus());
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
