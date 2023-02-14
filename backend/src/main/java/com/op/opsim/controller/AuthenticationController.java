package com.op.opsim.controller;

import com.auth0.jwt.algorithms.Algorithm;
import com.op.opsim.generated.AuthenticationError;
import com.op.opsim.generated.LoginRequest;
import com.op.opsim.generated.User;
import com.op.opsim.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/create")
    public ResponseEntity<? extends Object> create(@RequestParam String username, @RequestParam String password) {
        try {
            User user = userService.createUser(username, password);
            user.setPassword("********");
            user.setSalt(null);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        catch (DuplicateKeyException e) {
            String reason = String.format("Username '%s' is unavailable", username);
            return generateAuthenticateError(reason, HttpStatus.CONFLICT);
        }

    }


    @PostMapping(path = "/login")
    public ResponseEntity<? extends Object> login(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        if (username == null || password == null) {
            String reason = "Missing username and/or password";
            return generateAuthenticateError(reason, HttpStatus.UNAUTHORIZED);
        }

        User user = userService.findUser(username, password);
        if (user == null) {
            String reason = "Incorrect username and/or password";
            return generateAuthenticateError(reason, HttpStatus.UNAUTHORIZED);
        }

        String jwt = userService.issueJwt(username);
        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }

    private ResponseEntity<AuthenticationError> generateAuthenticateError(String reason, HttpStatus httpStatus) {
        AuthenticationError authenticationError = new AuthenticationError();
        authenticationError.setCode(httpStatus.value());
        authenticationError.setReason(reason);
        return new ResponseEntity<>(authenticationError, httpStatus);
    }

}
