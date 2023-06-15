package com.javarush.ivanilov.taskmanager.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "task")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "description", columnDefinition = "varchar(100)", nullable = false)
    private String description;

    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "status", nullable = false, columnDefinition = "int")
    private Status status;
}
