package com.microservice.notes_microservice.utilties;

import com.microservice.notes_microservice.exception.UserNotLoggedIn;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {
    public String generateJwt(String email, String name, Date date) throws UnsupportedEncodingException {

        String jwt = Jwts.builder()
                .setSubject(email)
                .setExpiration(date)
                .claim("name", name)
                .signWith(
                        SignatureAlgorithm.HS256,
                        "mySecretKey12345".getBytes("UTF-8")
                )
                .compact();

        return jwt;
    }


    public Map<String, Object> jwtToMap(String jwt) throws ExpiredJwtException, UnsupportedEncodingException, UserNotLoggedIn {
       try {
           Jws<Claims> claim = Jwts.parser()
                   .setSigningKey("mySecretKey12345".getBytes("UTF-8"))
                   .parseClaimsJws(jwt);

           String name = claim.getBody().get("name", String.class);

           Date expDate = claim.getBody().getExpiration();
           String email = claim.getBody().getSubject();

           Map<String, Object> userData = new HashMap<>();
           userData.put("email", email);
           userData.put("name", name);
           userData.put("exp_date", expDate);

           Date now = new Date();
           if (now.after(expDate)) {
               throw new ExpiredJwtException(null, null, "Session expired!");
           }

           return userData;
       } catch (ExpiredJwtException e) {
           throw new UserNotLoggedIn("Session expired!");
       }
    }

    public String getJwtFromHttpRequest(HttpServletRequest request) {
        String jwt = null;
        if (request.getHeader("jwt") != null) {
            jwt = request.getHeader("jwt");     //token present in header
        } else if (request.getCookies() != null) {
            Cookie[] cookies = request.getCookies();   //token present in cookie
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("jwt")) {
                    jwt = cookie.getValue();
                }
            }
        }
        return jwt;
    }
}
