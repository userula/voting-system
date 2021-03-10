package com.example.finalproj.repository;

import com.example.finalproj.repository.dto.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    void deleteQuestionByQuestionId(Long id);
    Question getQuestionByQuestionId(Long id);
}
