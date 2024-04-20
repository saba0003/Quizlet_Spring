package com.example.demo.services.implementations;

import com.example.demo.dtos.FlashcardDto;
import com.example.demo.models.Flashcard;
import com.example.demo.repositories.FlashcardRepository;
import com.example.demo.services.FlashcardService;
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
public class FlashcardServiceImpl implements FlashcardService {

    private static final Logger logger = LogManager.getLogger(FlashcardServiceImpl.class);

    private final FlashcardRepository flashcardRepository;

    @Override
    public Optional<FlashcardDto> getFlashcardById(Integer id) {
        Optional<Flashcard> flashcard = flashcardRepository.findById(id);
        return flashcard.map(this::mapToFlashcardDto);
    }

    @Override
    public List<FlashcardDto> getFlashcardsByFrontSideContent(String frontSideContent) {
        List<Flashcard> flashcards = flashcardRepository.findByFrontSideContent(frontSideContent);
        return flashcards.stream().map(this::mapToFlashcardDto).collect(Collectors.toList());
    }

    @Override
    public List<FlashcardDto> getFlashcardsByBackSideContent(String backSideContent) {
        List<Flashcard> flashcards = flashcardRepository.findByBackSideContent(backSideContent);
        return flashcards.stream().map(this::mapToFlashcardDto).collect(Collectors.toList());
    }

    @Override
    public List<FlashcardDto> getFlashcardsByContent(String content) {
        List<Flashcard> flashcards = flashcardRepository.findByContent(content);
        return flashcards.stream().map(this::mapToFlashcardDto).collect(Collectors.toList());
    }

    @Override
    public List<FlashcardDto> getFlashcards() {
        List<Flashcard> flashcards = flashcardRepository.findAll();
        return flashcards.stream().map(this::mapToFlashcardDto).collect(Collectors.toList());
    }

    @Override
    public void addNewFlashcard(FlashcardDto flashcardDto) {
        Flashcard flashcard = mapToFlashcardEntity(flashcardDto);
        flashcardRepository.save(flashcard);
    }

    @Override
    public void deleteFlashcard(Integer flashcardId) {
        flashcardRepository.deleteById(flashcardId);
    }

    @Override
    @Transactional
    public void updateFlashcard(FlashcardDto flashcardDto) {
        Flashcard flashcard = mapToFlashcardEntity(flashcardDto);
        flashcardRepository.save(flashcard);
    }

    /** AUX */
    private Flashcard mapToFlashcardEntity(FlashcardDto flashcardDto) {
        return Flashcard.builder()
                .id(flashcardDto.getId())
                .frontSideContent(flashcardDto.getFrontSideContent())
                .backSideContent(flashcardDto.getBackSideContent())
                .build();
    }

    /** AUX */
    private FlashcardDto mapToFlashcardDto(Flashcard flashcard) {
        return FlashcardDto.builder()
                .id(flashcard.getId())
                .frontSideContent(flashcard.getFrontSideContent())
                .backSideContent(flashcard.getBackSideContent())
                .build();
    }
}
