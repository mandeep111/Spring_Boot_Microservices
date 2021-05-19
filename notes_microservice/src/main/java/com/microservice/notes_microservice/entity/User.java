package com.microservice.notes_microservice.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="user")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "Email")
    @Getter @Setter
    private String email;

    @Column(name = "Name")
    @NotNull
    @Getter @Setter
    private String name;

    @Column(name = "Password")
    @NotNull
    @Getter @Setter
    private String password;
}
