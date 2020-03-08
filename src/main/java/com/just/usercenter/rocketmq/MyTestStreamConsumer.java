package com.just.usercenter.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MyTestStreamConsumer {

    /*@StreamListener(MySink.MY_INPUT)
    public void receive(String messageBody){
        log.info("自定义接口消费：通过stream收到了消息：messageBody={}",messageBody);
    }

    *//**
     * 全局异常处理
     * @param message
     *//*
    @StreamListener("errorChannel")
    public void error(Message<?> message) {
        ErrorMessage errorMessage = (ErrorMessage) message;
        log.warn("发生异常,errorMessage ={}",errorMessage);
    }*/

}
