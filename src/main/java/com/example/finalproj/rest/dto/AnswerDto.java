package com.example.finalproj.rest.dto;

import lombok.Data;

@Data
public class AnswerDto {
    private long answerId;
    private String answerText;
    private long questionId;
}
