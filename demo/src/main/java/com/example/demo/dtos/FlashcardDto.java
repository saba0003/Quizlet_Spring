package com.example.demo.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FlashcardDto {
    private Integer id;
    @NotBlank(message = "Content of a flashcard must not be empty!")
    private String frontSideContent;
    @NotBlank(message = "Content of a flashcard must not be empty!")
    private String backSideContent;
}
