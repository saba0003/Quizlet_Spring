package com.example.demo.controllers;

import com.example.demo.dtos.QuestionDto;
import com.example.demo.models.Question;
import com.example.demo.services.QuestionService;
import com.example.demo.util.Answer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Controller
@RequestMapping(path = "api/v1/questions")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("{questionId}")
    public String getSpecificQuestion(@PathVariable int questionId, Model model) {
        Optional<QuestionDto> questionDto = questionService.getQuestionById(questionId);
        questionDto.ifPresent(question -> model.addAttribute("question", question));
        return "questions/questions-single";
    }

    @GetMapping
    public String listQuestions(Model model) {
        List<QuestionDto> questions = questionService.getQuestions();
        model.addAttribute("questions", questions);
        return "questions/questions-list";
    }

    @GetMapping("new")
    public String createQuestionForm(Model model) {
        Question question = new Question();
        model.addAttribute("question", question);
        return "questions/questions-create";
    }

    @PostMapping("new")
    public String addQuestion(@Valid @ModelAttribute("question") QuestionDto questionDto,
                              BindingResult result, Model model) {
        validatePossibleAnswers(questionDto, result);

        // Check for validations
        if (result.hasErrors()) {
            model.addAttribute("question", questionDto);
            return "questions/questions-create";
        }

        questionService.addNewQuestion(questionDto);
        return "redirect:/api/v1/questions";
    }

    @GetMapping("{questionId}/delete")
    public String deleteQuestion(@PathVariable int questionId) {
        questionService.deleteQuestion(questionId);
        return "redirect:/api/v1/questions";
    }

    @GetMapping("{questionId}/edit")
    public String editQuestionForm(@PathVariable int questionId, Model model) {
        Optional<QuestionDto> questionDto = questionService.getQuestionById(questionId);
        questionDto.ifPresent(question -> model.addAttribute("question", question));
        return "questions/questions-edit";
    }

    @PostMapping("{questionId}/edit")
    public String editQuestion(@PathVariable int questionId,
                               @Valid @ModelAttribute("question") QuestionDto questionDto,
                               BindingResult result) {
        validatePossibleAnswers(questionDto, result);
        if (result.hasErrors())
            return "questions/questions-edit";
        questionDto.setId(questionId);
        questionService.updateQuestion(questionDto);
        return "redirect:/api/v1/questions";
    }

    @GetMapping("quiz")
    public String startQuiz(Model model) {
        List<QuestionDto> questions = questionService.getQuestions();
        model.addAttribute("questions", questions);
        return "questions/quiz";
    }

    /** AUX */
    private void validatePossibleAnswers(QuestionDto questionDto, BindingResult result) {
        List<Answer> possibleAnswers = questionDto.getPossibleAnswers();
        Set<String> answerContents = new HashSet<>();
        Set<String> duplicateContents = new HashSet<>();

        // Add error messages for empty answers
        for (int i = 0; i < possibleAnswers.size(); i++) {
            Answer answer = possibleAnswers.get(i);
            String content = answer.getContent();
            if (content == null || content.isEmpty()) {
                result.rejectValue("possibleAnswers[" + i + "].content", "NotBlank.questionDto.possibleAnswers[" + i + "].content", "Answer must not be empty");
            } else if (!answerContents.add(content)) {
                duplicateContents.add(content);
            }
        }

        // Add error messages for each duplicate content
        for (String duplicateContent : duplicateContents) {
            for (int i = 0; i < possibleAnswers.size(); i++) {
                Answer answer = possibleAnswers.get(i);
                String content = answer.getContent();
                if (duplicateContent.equals(content)) {
                    result.rejectValue("possibleAnswers[" + i + "].content", "Duplicate.questionDto.possibleAnswers[" + i + "].content", "Answer must be unique");
                }
            }
        }
    }
}
