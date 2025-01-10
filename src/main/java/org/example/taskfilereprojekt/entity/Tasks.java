package org.example.taskfilereprojekt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Tasks{

    @Id
    private Integer id;
    private String title;
    @ManyToOne
    private Users user;
    private Boolean status;
    private LocalDateTime date;

}
