package com.microservice.notes_microservice.controller;

import com.microservice.notes_microservice.entity.User;
import com.microservice.notes_microservice.exception.UserNotInDatabaseException;
import com.microservice.notes_microservice.service.LoginService;
import com.microservice.notes_microservice.utilties.JsonResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<JsonResponseBody> login(@RequestParam("email") String email, @RequestParam("password") String password) {
        try {
            Optional<User> dbUser = loginService.getUser(email, password);
            User user = dbUser.get();
            String jwt = loginService.createJwt(email, user.getName(), new Date());
            return ResponseEntity.status(HttpStatus.OK).header("jwt", jwt).body(new JsonResponseBody(HttpStatus.OK.value(), "Login successful!"));
        } catch (UserNotInDatabaseException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "Forbidden: " + e.toString()));
        } catch (UnsupportedEncodingException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "Forbidden: " + e.toString()));
        }
    }
}
