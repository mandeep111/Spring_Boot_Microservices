package com.microservice.notes_statistics_microservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name="note_statics")
@AllArgsConstructor
@NoArgsConstructor
public class NoteStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    @Column(name="ID")
    private int id;

    @Getter @Setter
    @Column(name="ID")
    private String description;

    @Getter @Setter
    @Column(name="ID")
    private String email;

    @Getter @Setter
    @Column(name="ID")
    private Date date;
}
