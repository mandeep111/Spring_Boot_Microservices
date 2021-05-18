package com.microservice.notes_microservice.service;

import com.microservice.notes_microservice.entity.User;
import com.microservice.notes_microservice.exception.UserNotInDatabaseException;
import com.microservice.notes_microservice.exception.UserNotLoggedIn;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

public interface LoginService {
    Optional<User> getUser(String email, String password) throws UserNotInDatabaseException;

    String createJwt(String email, String name, Date date);

    Map<String, Object> getDataAfterJwtVerified(HttpServletRequest request) throws UserNotLoggedIn;
}
