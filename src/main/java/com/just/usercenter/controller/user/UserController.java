package com.just.usercenter.controller.user;

import com.just.usercenter.domain.entity.user.User;
import com.just.usercenter.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author yanghao
 * @date 2020/1/19 13:50
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User findById(@PathVariable Integer id){
//        try {
//            TimeUnit.SECONDS.sleep(2);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return userService.findById(id);
    }


}
