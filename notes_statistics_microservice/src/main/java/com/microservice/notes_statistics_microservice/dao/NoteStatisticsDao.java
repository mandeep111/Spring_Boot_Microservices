package com.microservice.notes_statistics_microservice.dao;

import com.microservice.notes_statistics_microservice.entity.NoteStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoteStatisticsDao extends JpaRepository<NoteStatistics, Integer> {
    @Query(value="SELECT * FROM testdb WHERE EMAIL=:email ORDER BY DATE DESC;", nativeQuery = true)
    public List<NoteStatistics> getNoteStatistics(@Param("email") String email);
}
