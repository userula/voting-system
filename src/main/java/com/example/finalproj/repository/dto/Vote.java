package com.example.finalproj.repository.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.User;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long voteId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Account user;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;

    @Column(columnDefinition = "timestamp with time zone default NOW()")
    private Timestamp date;
}
