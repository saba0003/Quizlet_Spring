package com.example.demo.dtos;

import com.example.demo.util.Answer;
import com.example.demo.util.annotations.NonEmptyAnswer;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

@Data
@Builder
public class QuestionDto {

    private Integer id;

    @NotBlank(message = "Description of a question mustn't be empty!")
    private String description;

//    @UniqueElements(message = "Possible answers must be unique!")
//    @NonEmptyAnswer(message = "Answer must not be empty!")
    private List<Answer> possibleAnswers;

    @NotNull(message = "Correct answer must be provided!")
    private Integer correctAnswer;
}
