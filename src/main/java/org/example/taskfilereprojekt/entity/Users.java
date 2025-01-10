package org.example.taskfilereprojekt.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Users {

    @Id
    private Integer id;
    private String firstName;
    private String lastName;

}
