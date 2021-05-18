package com.microservice.notes_microservice.dao;

import com.microservice.notes_microservice.entity.UserNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserNoteDao extends JpaRepository<UserNote, Integer> {
}
