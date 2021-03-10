package com.example.finalproj.repository.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long questionId;

    private String questionText;


    @OneToMany(mappedBy = "question")
    private List<Answer> answerOptions;

    @Transient
    private long statistic = 0;
}
