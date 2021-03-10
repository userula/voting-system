package com.example.finalproj.service;

import com.example.finalproj.repository.AnswerRepository;
import com.example.finalproj.repository.QuestionRepository;
import com.example.finalproj.repository.VoteRepository;
import com.example.finalproj.repository.dto.Account;
import com.example.finalproj.repository.dto.Answer;
import com.example.finalproj.repository.dto.Question;
import com.example.finalproj.repository.dto.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class QuestionService {
    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;
    private VoteRepository voteRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, AnswerRepository answerRepository, VoteRepository voteRepository){
        this.questionRepository = questionRepository;
        this.voteRepository = voteRepository;
        this.answerRepository = answerRepository;
    }

    public List<Question> getAllAnsweredQuestions(Account account) {
        List<Vote> userVotes = voteRepository.findAllByUserUserId(account.getUserId());
        List<Vote> votes = voteRepository.findAll();
        List<Question> questions = questionRepository.findAll();
        for (Question q : questions) {
            for (Vote v : votes) {
                if (v.getAnswer().getQuestion() == q) {
                    q.setStatistic(q.getStatistic()+1);
                }
            }
        }
        return getAnsweredQuestions(questions, userVotes);
    }

    public List<Answer> getAllAnsweredAnswers(Account account) {
        List<Question> questions = questionRepository.findAll();
        List<Vote> votes = voteRepository.findAll();
        List<Vote> userVotes = voteRepository.findAllByUserUserId(account.getUserId());
        List<Answer> answers = new ArrayList<>();
        List<Question> r = getAnsweredQuestions(questions, userVotes);
        for (Question q : r) {
            for (Answer a : q.getAnswerOptions()) {
                for (Vote v : votes) {
                    if (v.getAnswer().getAnswerId() == a.getAnswerId()) {
                        a.setStatistic(a.getStatistic()+1);
                    }
                }
                a.setStatistic(a.getStatistic()/q.getStatistic()*100);
                answers.add(a);
            }
        }
        return answers;
    }

    private List<Question> getAnsweredQuestions(List<Question> questions, List<Vote> votes) {
        List<Question> r = new ArrayList<>();
        for (Question q : questions) {
            for (Vote v : votes) {
                if (v.getAnswer().getQuestion().getQuestionId() == q.getQuestionId()) {
                    r.add(q);
                }
            }
        }
        return r;
    }

    public List<Question> getAllNonAnsweredQuestions(Account account) {
        List<Vote> votes = voteRepository.findAllByUserUserId(account.getUserId());
        List<Question> questions = questionRepository.findAll();
        for (Vote v : votes) {
            for (Question q : questions) {
                if (v.getAnswer().getQuestion() == q) {
                    questions.remove(q);
                    break;
                }
            }
        }

        return questions;
    }

    public List<Question> getAllQuestions(){
        return questionRepository.findAll();
    }
    public List<Answer> getAllAnswers(){
        return answerRepository.findAll();
    }
    public void setVote(long a, Account u){
        Vote vote = new Vote();
        Answer ans = answerRepository.getAnswerByAnswerId(a);
        vote.setAnswer(ans);
        vote.setDate(new Timestamp(new Date().getTime()));
        vote.setUser(u);
        voteRepository.save(vote);
    }
    public void registerQuestion(Question question){
        questionRepository.save(question);
    }
    public void registerAnswers(Question question){
        for (Answer a : question.getAnswerOptions()) {
            a.setQuestion(question);
            answerRepository.save(a);
        }
    }
    @Transactional
    public void deleteQuestion(Long id){
        List<Answer> answers = answerRepository.getAnswersByQuestion(questionRepository.getQuestionByQuestionId(id));
        for (Answer a: answers
             ) {
            voteRepository.deleteVotesByAnswer(a);
        }
        answerRepository.deleteAnswersByQuestion(questionRepository.getQuestionByQuestionId(id));
        questionRepository.deleteQuestionByQuestionId(id);
    }

    public Question getQuestion(Long id){
        return questionRepository.getQuestionByQuestionId(id);
    }
    public void updateQuestion(Long id, Question question){
        Question updatedQ = questionRepository.findById(id).get();
        updatedQ.setQuestionText(question.getQuestionText());
        questionRepository.save(updatedQ);
    }
    public void updateAnswers(Long id, Question question){
        List<Answer> answers = answerRepository.getAnswersByQuestion(questionRepository.getQuestionByQuestionId(id));
        List<Answer> newAnswers = question.getAnswerOptions();
        int i = 0;
        for (Answer a: answers) {
            a.setAnswerText(newAnswers.get(i).getAnswerText());
            answerRepository.save(a);
            System.out.println(newAnswers.get(i).getAnswerText());
            i++;
        }

    }
    public List<Answer> getAllAnswersByQuestionId(Long id){
        return answerRepository.getAnswersByQuestion(questionRepository.getQuestionByQuestionId(id));
    }

}
