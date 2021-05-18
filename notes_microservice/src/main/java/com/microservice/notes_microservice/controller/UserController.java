package com.microservice.notes_microservice.controller;

import com.microservice.notes_microservice.entity.User;
import com.microservice.notes_microservice.exception.UserNotInDatabaseException;
import com.microservice.notes_microservice.exception.UserNotLoggedIn;
import com.microservice.notes_microservice.service.LoginService;
import com.microservice.notes_microservice.service.UserNoteService;
import com.microservice.notes_microservice.utilties.JsonResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private UserNoteService userNoteService;

    @PostMapping("/login")
    public ResponseEntity<JsonResponseBody> login(@RequestParam("email") String email, @RequestParam("password") String password) {
        try {
            Optional<User> dbUser = loginService.getUser(email, password);
            User user = dbUser.get();
            String jwt = loginService.createJwt(email, user.getName(), new Date());
            return ResponseEntity.status(HttpStatus.OK).header("jwt", jwt).body(new JsonResponseBody(HttpStatus.OK.value(), "Login successful!"));
        } catch (UserNotInDatabaseException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "User not found!"));
        } catch (UnsupportedEncodingException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "Forbidden: " + e.toString()));
        }
    }

    @GetMapping("/showNotes")
    public ResponseEntity<JsonResponseBody> showUserNotes(HttpServletRequest request) {
        try {
            Map<String, Object> userData = loginService.getDataAfterJwtVerified(request);
            return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), userNoteService.getNotes((String) userData.get("email"))));
        } catch (UnsupportedEncodingException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "Forbidden: " + e.toString()));
        } catch (UserNotLoggedIn e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "User not logged in!"));
        }
    }

    @GetMapping("/deleteNote/{id}")
    public ResponseEntity<JsonResponseBody> deleteNotes(HttpServletRequest request, @PathVariable("id") int id) {
        try {
            Map<String, Object> userData = loginService.getDataAfterJwtVerified(request);
            userNoteService.deleteNote(id);
            return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), "Note deleted!"));
        } catch (UnsupportedEncodingException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "Forbidden: " + e.toString()));
        } catch (UserNotLoggedIn e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "User not logged in!"));
        }
    }
}
