package com.microservice.notes_microservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="user_note")
@NoArgsConstructor
@AllArgsConstructor
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
    private String priority;

    @Column(name = "FK_User")
    @Getter @Setter
    private String fkUser;

    @PrePersist
    void getDate() {
        this.date = new Date();
    }
}
