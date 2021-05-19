package com.microservice.notes_statistics_microservice.service;

import com.microservice.notes_statistics_microservice.dao.NoteStatisticsDao;
import com.microservice.notes_statistics_microservice.entity.NoteStatistics;
import com.microservice.notes_statistics_microservice.utilities.JsonResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;

public class NoteStatisticsServiceImpl implements NoteStatisticsService{

    @Autowired
    private NoteStatisticsDao noteStatisticsDao;

    @Override
    public List<NoteStatistics> getStatistics(String jwt, String email) {

        return null;
    }

    public List<LinkedHashMap> getDataFromNoteMicroservice(String jwt) {
        // preparing header with jwt
        MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
        header.add("jwt", jwt);
        HttpEntity<?> request = new HttpEntity<>(String.class, header);

        // calling another microservice
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JsonResponseBody> responseEntity = restTemplate.exchange("http://localhost:8081/showNotes",
                HttpMethod.POST, request,JsonResponseBody.class);

        List<LinkedHashMap> notes = (List) responseEntity.getBody().getResponse();
        return notes;
    }
}
