package com.example.demo.services;

import com.example.demo.dtos.QuestionDto;

import java.util.List;
import java.util.Optional;

public interface QuestionService {
    Optional<QuestionDto> getQuestionById(Integer questionId);
    List<QuestionDto> getQuestionsByDescription(String description);
    List<QuestionDto> getQuestionsByPossibleAnswer(String possibleAnswer);
    List<QuestionDto> getQuestions();
    void addNewQuestion(QuestionDto questionDto);
    void deleteQuestion(Integer questionId);
    void updateQuestion(QuestionDto questionDto);
}
