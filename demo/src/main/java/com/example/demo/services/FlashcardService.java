package com.example.demo.services;


import com.example.demo.dtos.FlashcardDto;

import java.util.List;
import java.util.Optional;

public interface FlashcardService {
    Optional<FlashcardDto> getFlashcardById(Integer id);
    List<FlashcardDto> getFlashcardsByFrontSideContent(String frontSideContent);
    List<FlashcardDto> getFlashcardsByBackSideContent(String backSideContent);
    List<FlashcardDto> getFlashcardsByContent(String content);
    List<FlashcardDto> getFlashcards();
    void addNewFlashcard(FlashcardDto flashcardDto);
    void deleteFlashcard(Integer flashcardId);
    void updateFlashcard(FlashcardDto flashcardDto);
}
