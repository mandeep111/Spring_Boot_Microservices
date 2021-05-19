package com.microservice.notes_statistics_microservice;

import com.microservice.notes_statistics_microservice.dao.NoteStatisticsDao;
import com.microservice.notes_statistics_microservice.entity.NoteStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class NotesStatisticsMicroserviceApplication implements CommandLineRunner {

	@Autowired
	private NoteStatisticsDao noteStatisticsDao;

	public static void main(String[] args) {
		SpringApplication.run(NotesStatisticsMicroserviceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		noteStatisticsDao.save(new NoteStatistics(1, "Description of note", "mandeepdhakal@gmail.com", new Date()));
		noteStatisticsDao.save(new NoteStatistics(2, "Description of note1", "ram@gmail.com", new Date()));
		noteStatisticsDao.save(new NoteStatistics(3, "Description of note2", "shyam@gmail.com", new Date()));
	}
}
