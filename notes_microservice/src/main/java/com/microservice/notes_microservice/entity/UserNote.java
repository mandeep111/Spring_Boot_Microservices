package com.microservice.notes_microservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="user_note")
public class UserNote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "Description")
    private String description;

    @Column(name = "Date")
    private Date date;

    @Column(name = "Priority")
    private String priority;

    @Column(name = "FK_User")
    @Getter @Setter
    private String fkUser;

    public UserNote() {
    }

    public UserNote(int id, String description, Date date, String priority, String fkUser) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.priority = priority;
        this.fkUser = fkUser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getFkUser() {
        return fkUser;
    }

    public void setFkUser(String fkUser) {
        this.fkUser = fkUser;
    }

    @PrePersist
    void getDate() {
        this.date = new Date();
    }
}
