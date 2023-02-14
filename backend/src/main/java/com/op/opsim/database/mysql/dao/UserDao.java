package com.op.opsim.database.mysql.dao;

import com.op.opsim.database.mysql.mapper.UserMapper;
import com.op.opsim.generated.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDao {

    @Autowired
    private UserMapper userMapper;

    public void createUser(User user) {
        userMapper.createUser(user);
    }

}
