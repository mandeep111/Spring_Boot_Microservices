package com.microservice.notes_statistics_microservice.dao;

import com.microservice.notes_statistics_microservice.entity.NoteStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteStatisticsDao extends JpaRepository<NoteStatistics, Integer> {
}
