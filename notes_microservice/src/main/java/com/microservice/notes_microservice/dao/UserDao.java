package com.microservice.notes_microservice.dao;

import com.microservice.notes_microservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, String> {
}
