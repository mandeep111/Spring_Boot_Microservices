package com.microservice.notes_microservice.service;

import com.microservice.notes_microservice.dao.UserDao;
import com.microservice.notes_microservice.entity.User;
import com.microservice.notes_microservice.exception.UserNotInDatabaseException;
import com.microservice.notes_microservice.exception.UserNotLoggedIn;
import com.microservice.notes_microservice.utilties.EncryptionUtils;
import com.microservice.notes_microservice.utilties.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService{
    @Autowired
    private UserDao userDao;

    @Autowired
    private EncryptionUtils encryptionUtils;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public Optional<User> getUser(String email, String password) throws UserNotInDatabaseException {
        Optional<User> dbUser = userDao.findByEmail(email);
        if (dbUser.isPresent()) {
            User user = dbUser.get();
            if (!encryptionUtils.decrypt(user.getPassword()).equals(password)) {
                throw new UserNotInDatabaseException("Wrong email or password");
            }
        } else {
            throw new UserNotInDatabaseException("No user found in database");
        }
        return dbUser;
    }

    @Override
    public String createJwt(String email, String name, Date date) throws UnsupportedEncodingException {
        date.setTime(date.getTime() + (60*100*1000));
        return jwtUtils.generateJwt(email, name, date);
    }

    @Override
    public Map<String, Object> getDataAfterJwtVerified(HttpServletRequest request) throws UserNotLoggedIn, UnsupportedEncodingException {
        String jwt = jwtUtils.getJwtFromHttpRequest(request);
        if (jwt == null) {
            throw new UserNotLoggedIn("User not logged in!");
        }
        return jwtUtils.jwtToMap(jwt);
    }
}
