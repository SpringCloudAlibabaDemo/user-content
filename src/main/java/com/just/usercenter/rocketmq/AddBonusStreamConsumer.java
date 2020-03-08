package com.just.usercenter.rocketmq;

import com.just.usercenter.domain.dto.messaging.UserAddBonusMsgDTO;
import com.just.usercenter.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AddBonusStreamConsumer {

    @Autowired
    private UserService userService;




    @StreamListener(Sink.INPUT)
    public void receive(UserAddBonusMsgDTO message){
       userService.addBonus(message);
    }
}
