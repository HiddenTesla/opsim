package com.op.opsim.controller;

import com.op.opsim.generated.AuthenticationError;
import com.op.opsim.generated.User;
import com.op.opsim.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/create")
    public ResponseEntity<Object> create(@RequestParam String username, @RequestParam String password) {
        try {
            User user = userService.createUser(username, password);
            user.setPassword("********");
            user.setSalt(null);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        catch (DuplicateKeyException e) {
            String reason = String.format("Username '%s' is unavailable", username);
            HttpStatus conflict = HttpStatus.CONFLICT;

            AuthenticationError authenticationError = new AuthenticationError();
            authenticationError.setCode(conflict.value());
            authenticationError.setReason(reason);

            return new ResponseEntity<>(authenticationError, conflict);
        }

    }

}
