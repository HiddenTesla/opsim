package com.op.opsim.database.mysql.mapper;

import com.op.opsim.generated.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

public interface UserMapper {

    @Options(keyProperty = "uid", keyColumn = "uid", useGeneratedKeys = true)
    @Insert(" INSERT INTO user " +
            "(uid, username, password, salt) " +
            "VALUES" +
            "(#{uid}, #{username}, #{password}, #{salt})")
    void createUser(User entity);
}
