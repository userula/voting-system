package com.example.finalproj.util;

import com.example.finalproj.repository.dto.Account;
import com.example.finalproj.repository.dto.Answer;
import com.example.finalproj.repository.dto.Question;
import com.example.finalproj.rest.dto.AnswerWebDto;
import com.example.finalproj.rest.dto.QuestionDto;
import com.example.finalproj.rest.dto.QuestionWebDto;
import com.example.finalproj.rest.dto.UserDto;

import java.util.ArrayList;
import java.util.List;


public class Converter {
    public UserDto accountToUserDto(Account account) {
        UserDto dto = new UserDto();
        dto.setUserId(account.getUserId());
        dto.setAge(account.getAge());
        dto.setFirstName(account.getFirstName());
        dto.setLastName(account.getLastName());
        dto.setGroupName(account.getGroupName());
        dto.setInterest(account.getInterest());
        dto.setUsername(account.getUsername());
        dto.setRoleId(account.getRole().getRoleId());
        return dto;
    }

    public QuestionDto questionToQuestionDto(Question q) {
        QuestionDto dto = new QuestionDto();
        dto.setQuestionId(q.getQuestionId());
        dto.setQuestionText(q.getQuestionText());
        return dto;
    }

    public List<AnswerWebDto> answersToAnswerWebDto(List<Answer> answers) {
        List<AnswerWebDto> list = new ArrayList<>();
        for (Answer a:
             answers) {
            AnswerWebDto dto = new AnswerWebDto();
            dto.setAnswerId(a.getAnswerId());
            dto.setAnswerText(a.getAnswerText());
            list.add(dto);
        }
        return list;
    }
}
