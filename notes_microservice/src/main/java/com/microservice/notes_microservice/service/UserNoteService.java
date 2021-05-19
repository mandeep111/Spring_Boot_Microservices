package com.microservice.notes_microservice.service;

import com.microservice.notes_microservice.entity.UserNote;

import java.util.List;

public interface UserNoteService {
    List<UserNote> getNotes(String email);

    void deleteNote(int id);

    UserNote addNote(UserNote note);
}
