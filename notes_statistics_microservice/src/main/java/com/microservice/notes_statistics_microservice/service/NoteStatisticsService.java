package com.microservice.notes_statistics_microservice.service;

import com.microservice.notes_statistics_microservice.entity.NoteStatistics;
import org.springframework.stereotype.Service;

import java.util.List;

public interface NoteStatisticsService {
    List<NoteStatistics> getStatistics(String jwt, String email);
}
