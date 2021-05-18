package com.microservice.notes_microservice.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="user")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "Email")
    @Getter
    @Setter
    @NonNull
    private String email;

    @Column(name = "Name")
    @Getter
    @Setter
    private String name;

    @Column(name = "Password")
    @Getter
    @Setter
    private String password;
}
