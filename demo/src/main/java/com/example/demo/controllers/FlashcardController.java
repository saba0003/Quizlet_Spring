package com.example.demo.controllers;

import com.example.demo.dtos.FlashcardDto;
import com.example.demo.models.Flashcard;
import com.example.demo.services.FlashcardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping(path = "/api/v1/flashcards")
public class FlashcardController {

    private final FlashcardService flashcardService;

    @GetMapping("{flashcardId}")
    public String getSpecificFlashcard(@PathVariable int flashcardId, Model model) {
        Optional<FlashcardDto> flashcardDto = flashcardService.getFlashcardById(flashcardId);
        flashcardDto.ifPresent(flashcard -> model.addAttribute("flashcard", flashcard));
        return "flashcards/flashcards-single";
    }

    @GetMapping
    public String listFlashcards(Model model) {
        List<FlashcardDto> flashcards = flashcardService.getFlashcards();
        model.addAttribute("flashcards", flashcards);
        return "flashcards/flashcards-list";
    }

    @GetMapping("new")
    public String createFlashcardForm(Model model) {
        Flashcard flashcard = new Flashcard();
        model.addAttribute("flashcard", flashcard);
        return "flashcards/flashcards-create";
    }

    @PostMapping("new")
    public String addFlashcard(@Valid @ModelAttribute("flashcard") FlashcardDto flashcardDto,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("flashcard", flashcardDto);
            return "flashcards/flashcards-create";
        }
        flashcardService.addNewFlashcard(flashcardDto);
        return "redirect:/api/v1/flashcards";
    }

    @GetMapping("{flashcardId}/delete")
    public String deleteFlashcard(@PathVariable int flashcardId) {
        flashcardService.deleteFlashcard(flashcardId);
        return "redirect:/api/v1/flashcards";
    }

    @GetMapping("{flashcardId}/edit")
    public String editFlashcardForm(@PathVariable int flashcardId, Model model) {
        Optional<FlashcardDto> flashcardDto = flashcardService.getFlashcardById(flashcardId);
        flashcardDto.ifPresent(flashcard -> model.addAttribute("flashcard", flashcard));
        return "flashcards/flashcards-edit";
    }

    @PostMapping("{flashcardId}/edit")
    public String editFlashcard(@PathVariable int flashcardId,
                                @Valid @ModelAttribute("flashcard") FlashcardDto flashcardDto,
                                BindingResult result) {
        if (result.hasErrors())
            return "flashcards/flashcards-edit";
        flashcardDto.setId(flashcardId);
        flashcardService.updateFlashcard(flashcardDto);
        return "redirect:/api/v1/flashcards";
    }
}
