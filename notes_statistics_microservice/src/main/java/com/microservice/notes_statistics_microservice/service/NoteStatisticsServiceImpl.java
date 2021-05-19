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

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public class NoteStatisticsServiceImpl implements NoteStatisticsService{

    @Autowired
    private NoteStatisticsDao noteStatisticsDao;

    @Override
    public List<NoteStatistics> getStatistics(String jwt, String email) {
        List<LinkedHashMap> notes = getDataFromNoteMicroservice(jwt);

        // calculate statistics
        String description = "No stats";
        if (notes != null && notes.size() > 0) {
            int lowPriority = 0;
            int highPriority = 0;
            for (int i=0; i<notes.size(); i++) {
                LinkedHashMap note = notes.get(i);
                String priority = (String) note.get("priority");
                if (priority.equalsIgnoreCase("low")) lowPriority++;
                if (priority.equalsIgnoreCase("high")) highPriority++;

            }
            description =  lowPriority +" low priority and "+ highPriority+ " high priority notes.";
        }
        List<NoteStatistics> noteStatistics = noteStatisticsDao.getNoteStatistics(email);
        if (noteStatistics.size() > 0) {
            Date now = new Date();
            long diff = now.getTime() - noteStatistics.get(0).getDate().getTime();

            long days = diff/(24 * 60 * 60 * 1000);
            if(days>1){
                noteStatistics.add(noteStatisticsDao.save(new NoteStatistics(null, description, email, new Date())));
            }
        }
        return noteStatistics;
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
