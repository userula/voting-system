package com.example.finalproj.rest.dto;

import com.example.finalproj.repository.dto.Answer;
import com.example.finalproj.repository.dto.Question;
import lombok.Data;

import java.util.List;

@Data
public class QuestionWebDto {
    private QuestionDto question;
    private List<AnswerWebDto> answers;
}
