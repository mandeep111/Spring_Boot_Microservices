package com.microservice.notes_microservice.dao;

import com.microservice.notes_microservice.entity.UserNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserNoteDao extends JpaRepository<UserNote, Integer> {
    List<UserNote> findNoteByFkUser(String email);
}
