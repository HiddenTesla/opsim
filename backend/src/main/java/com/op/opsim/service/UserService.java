package com.op.opsim.service;

import com.op.opsim.database.mysql.dao.UserDao;
import com.op.opsim.generated.User;
import com.op.opsim.model.common.Digest;
import com.op.opsim.model.common.Seasoning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Autowired
    private UserDao userDao;

    private Seasoning seasoning = new Seasoning();

    public User createUser(String username, String plainPassword) {
        User user = new User();
        user.setUsername(username);
        String salt = seasoning.generateSalt(1 << 6);
        String saltyPassword = saltify(plainPassword, salt);
        String digestPassword = Digest.sha1sum(saltyPassword);
        user.setPassword(digestPassword);
        user.setSalt(salt);

        userDao.createUser(user);
        return user;
    }

    public User findUser(String username, String plainPassword) {
        User user = userDao.findUser(username);
        if (user == null)
            return null;

        String salt = user.getSalt();
        String saltyPassword = saltify(plainPassword, salt);
        String digestPassword = Digest.sha1sum(saltyPassword);

        if (!digestPassword.equals(user.getPassword()))
            return null;

        return user;
    }

    private String saltify(String plaintext, String salt) {
        return String.format("%s-%s", plaintext, salt);
    }
}
