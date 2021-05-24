package com.yc.mapper.first;

import com.yc.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author yucheng
 * @Date 2021/3/7 19:31
 */
@Repository
public interface UserMapper {

    int insertWithFields(User user);

    List<User> findByName(String name);

    int delete(int id);
}
