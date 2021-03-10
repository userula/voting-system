package com.example.finalproj.rest;

import com.example.finalproj.repository.AnswerRepository;
import com.example.finalproj.repository.dto.Question;
import com.example.finalproj.repository.dto.Vote;
import com.example.finalproj.rest.dto.QuestionWebDto;
import com.example.finalproj.rest.dto.VoteDto;
import com.example.finalproj.service.AccountDetailsService;
import com.example.finalproj.service.QuestionService;
import com.example.finalproj.service.UserService;
import com.example.finalproj.service.VoteService;
import com.example.finalproj.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/votes")
public class VoteResource {
    private QuestionService questionService;
    private AnswerRepository answerRepository;
    private UserService userService;
    private final VoteService voteService;
    private final AccountDetailsService accountDetailsService;
    @Autowired
    public VoteResource(UserService userService, QuestionService questionService, AnswerRepository answerRepository, VoteService voteService, AccountDetailsService accountDetailsService) {
        this.questionService = questionService;
        this.answerRepository = answerRepository;
        this.userService = userService;
        this.voteService = voteService;
        this.accountDetailsService = accountDetailsService;
    }

    @GetMapping
    public List<QuestionWebDto> getQuestions() {
        List<QuestionWebDto> dtoList = new ArrayList<>();
        List<Question> questions = questionService.getAllNonAnsweredQuestions(accountDetailsService.getAccount());
        Converter converter = new Converter();
        for (Question q:
             questions) {
            QuestionWebDto dto = new QuestionWebDto();
            dto.setQuestion(converter.questionToQuestionDto(q));
            dto.setAnswers(converter.answersToAnswerWebDto(q.getAnswerOptions()));
            dtoList.add(dto);
        }
        return dtoList;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void vote(@RequestBody VoteDto vote) {
        questionService.setVote(vote.getAnswerId(), accountDetailsService.getAccount());
    }
}
