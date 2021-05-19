package com.microservice.notes_statistics_microservice.controller;

import com.microservice.notes_statistics_microservice.service.NoteStatisticsService;
import com.microservice.notes_statistics_microservice.utilities.JsonResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoteController {
    @Autowired
    private NoteStatisticsService noteStatisticsService;

    @GetMapping("/getStats")
    public ResponseEntity<JsonResponseBody> getStatistics(@RequestParam("jwt") String jwt,
                                                          @RequestParam("email") String email) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), noteStatisticsService.getStatistics(jwt, email)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "Resource not found!"));
        }
    }
}
