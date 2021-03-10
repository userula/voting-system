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
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long answerId;

    private String answerText;

    @OneToMany(mappedBy = "answer")
    private List<Vote> votes;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Transient
    private float statistic = 0;
}
