package com.yc.service;

import com.yc.entity.User;
import com.yc.mapper.first.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author yucheng
 * @Date 2021/3/7 19:33
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User insertWithFields(User user) {
        userMapper.insertWithFields(user);
        return user;
    }

    public List<User> findByName(String name) {
        return userMapper.findByName(name);
    }

    public int delete(int id) {
        return userMapper.delete(id);
    }
}
