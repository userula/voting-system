package com.example.finalproj.repository;

import com.example.finalproj.repository.dto.Answer;
import com.example.finalproj.repository.dto.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
   List<Answer> getAnswersByQuestion(Question question);
   List<Answer> getAnswersByQuestionIn(List<Question> questions);
   Answer getAnswerByAnswerId(long id);
   void deleteAnswersByQuestion(Question q);
}
