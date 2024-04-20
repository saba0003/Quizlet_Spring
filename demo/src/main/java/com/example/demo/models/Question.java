package com.example.demo.models;

import com.example.demo.util.Answer;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Answer> possibleAnswers;
    private int correctAnswer;
    @Transient
    private int numberOfPossibleAnswers;

    public Question(String description, List<Answer> possibleAnswers, int correctAnswer) {
        this.description = description;
        this.possibleAnswers = possibleAnswers;
        this.correctAnswer = correctAnswer;
    }

    public int getNumberOfPossibleAnswers() {
        return possibleAnswers.size();
    }
}
