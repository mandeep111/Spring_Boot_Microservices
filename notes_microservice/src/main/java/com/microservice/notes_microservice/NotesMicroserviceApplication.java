package com.microservice.notes_microservice;

import com.microservice.notes_microservice.dao.UserDao;
import com.microservice.notes_microservice.dao.UserNoteDao;
import com.microservice.notes_microservice.entity.User;
import com.microservice.notes_microservice.entity.UserNote;
import com.microservice.notes_microservice.utilties.EncryptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class NotesMicroserviceApplication implements CommandLineRunner {
    @Autowired
	private UserDao userDao;

    @Autowired
	private UserNoteDao userNoteDao;

    @Autowired
	private EncryptionUtils encryptionUtils;

    public static void main(String[] args) {
        SpringApplication.run(NotesMicroserviceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
		String encryptedPassword;
		encryptedPassword = encryptionUtils.encrypt("Hello");
		userDao.save(new User("mandeepdhakal@gmail.com", "Mandeep Dhakal", encryptedPassword));

		encryptedPassword = encryptionUtils.encrypt("admin");
		userDao.save(new User("ram@gmail.com", "Ram Sharma", encryptedPassword));

		userNoteDao.save( new UserNote(1, "Learn Java", null, "high", "mandeepdhakal@gmail.com"));
		userNoteDao.save( new UserNote(2, "Learn Python", new Date(), "high", "ram@gmail.com"));
		userNoteDao.save( new UserNote(3, "Learn Dotnet", null, "medium", "mandeepdhakal@gmail.com"));
		userNoteDao.save( new UserNote(4, "Learn Flutter", new Date(), "low", "mandeepdhakal@gmail.com"));
    }
}
