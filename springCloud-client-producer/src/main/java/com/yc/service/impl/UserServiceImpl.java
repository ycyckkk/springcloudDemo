package com.yc.service.impl;

import com.yc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yuche on 2019/9/7.
 */
@Service("userService")
public class UserServiceImpl implements com.yc.service.UserService {
    public User getUser(String id) {
        User user = User.builder().id(id).name("QQ").pwd("chengyu930827").build();
        return user;
    }
}