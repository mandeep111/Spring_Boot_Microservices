package com.microservice.notes_statistics_microservice.dao;

import com.microservice.notes_statistics_microservice.entity.NoteStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteStatisticsDao extends JpaRepository<NoteStatistics, Integer> {
    @Query(value="SELECT * FROM note_statics WHERE EMAIL=:email ORDER BY DATE DESC;", nativeQuery = true)
    public List<NoteStatistics> getNoteStatistics(@Param("email") String email);
}
