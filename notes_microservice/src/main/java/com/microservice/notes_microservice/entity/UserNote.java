package com.microservice.notes_microservice.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="user_note")
public class UserNote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    @Getter @Setter
    private int id;

    @Column(name = "Description")
    @Getter @Setter
    private String description;

    @Column(name = "Date")
    @Getter @Setter
    private Date date;

    @Column(name = "Priority")
    @Getter @Setter
    @NotNull
    private String priority;

    @Column(name = "FK_User")
    @Getter @Setter
    @NotNull
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
}
