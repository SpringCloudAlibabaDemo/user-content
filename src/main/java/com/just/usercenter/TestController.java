package com.just.usercenter;

import com.just.usercenter.dao.user.UserMapper;
import com.just.usercenter.domain.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author yanghao
 * @date 2020/1/18 17:03
 */

@RestController
public class TestController {

    @Autowired
    private UserMapper userMapper;
    @GetMapping("/test")
    public User testInsert(){
        User user = new User();
        user.setAvatarUrl("xxx");
        user.setBonus(100);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userMapper.insertSelective(user);
        return user;
    }
}
