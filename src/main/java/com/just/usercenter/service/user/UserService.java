package com.just.usercenter.service.user;

import com.just.usercenter.dao.user.UserMapper;
import com.just.usercenter.domain.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yanghao
 * @date 2020/1/19 13:36
 */

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findById(Integer id){

        return userMapper.selectByPrimaryKey(id);

    }

}
