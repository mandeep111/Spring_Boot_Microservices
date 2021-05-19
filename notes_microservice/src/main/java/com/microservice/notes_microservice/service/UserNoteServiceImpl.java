package com.microservice.notes_microservice.service;

import com.microservice.notes_microservice.dao.UserNoteDao;
import com.microservice.notes_microservice.entity.UserNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserNoteServiceImpl implements UserNoteService{

    @Autowired
    private UserNoteDao userNoteDao;

    @Override
    public List<UserNote> getNotes(String email) {
        return userNoteDao.findNoteByFkUser(email);
    }

    @Override
    public void deleteNote(int id) {
        userNoteDao.deleteById(id);
    }

    @Override
    public UserNote addNote(UserNote note) {
        return userNoteDao.save(note);
    }
}
