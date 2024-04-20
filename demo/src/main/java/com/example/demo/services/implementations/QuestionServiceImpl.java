package com.example.demo.services.implementations;

import com.example.demo.dtos.QuestionDto;
import com.example.demo.models.Question;
import com.example.demo.repositories.QuestionRepository;
import com.example.demo.services.QuestionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QuestionServiceImpl implements QuestionService {

    private static final Logger logger = LogManager.getLogger(QuestionServiceImpl.class);

    private final QuestionRepository questionRepository;

    @Override
    public Optional<QuestionDto> getQuestionById(Integer questionId) {
        Optional<Question> question = questionRepository.findById(questionId);
        return question.map(this::mapToQuestionDto);
    }

    @Override
    public List<QuestionDto> getQuestionsByDescription(String description) {
        List<Question> questions = questionRepository.findByDescription(description);
        return questions.stream().map(this::mapToQuestionDto).collect(Collectors.toList());
    }

    @Override
    public List<QuestionDto> getQuestionsByPossibleAnswer(String possibleAnswer) {
        List<Question> questions = questionRepository.findByPossibleAnswer(possibleAnswer);
        return questions.stream().map(this::mapToQuestionDto).collect(Collectors.toList());
    }

    @Override
    public List<QuestionDto> getQuestions() {
        List<Question> questions = questionRepository.findAll();
        return questions.stream().map(this::mapToQuestionDto).collect(Collectors.toList());
    }

    @Override
    public void addNewQuestion(QuestionDto questionDto) {
        Question question = mapToQuestionEntity(questionDto);
        questionRepository.save(question);
    }

    @Override
    public void deleteQuestion(Integer questionId) {
        questionRepository.deleteById(questionId);
    }

    @Override
    @Transactional
    public void updateQuestion(QuestionDto questionDto) {
        Question question = mapToQuestionEntity(questionDto);
        questionRepository.save(question);
    }

    /** AUX */
    private Question mapToQuestionEntity(QuestionDto questionDto) {
        return Question.builder()
                .id(questionDto.getId())
                .description(questionDto.getDescription())
                .possibleAnswers(questionDto.getPossibleAnswers())
                .correctAnswer(questionDto.getCorrectAnswer())
                .build();
    }

    /** AUX */
    private QuestionDto mapToQuestionDto(Question question) {
        return QuestionDto.builder()
                .id(question.getId())
                .description(question.getDescription())
                .possibleAnswers(question.getPossibleAnswers())
                .correctAnswer(question.getCorrectAnswer())
                .build();
    }
}
