package com.example.demo.models;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "flashcards")
public class Flashcard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String frontSideContent;
    private String backSideContent;

    public Flashcard(String frontSideContent, String backSideContent) {
        this.frontSideContent = frontSideContent;
        this.backSideContent = backSideContent;
    }
}
