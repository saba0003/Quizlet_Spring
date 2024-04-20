package com.example.demo.repositories;

import com.example.demo.models.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlashcardRepository extends JpaRepository<Flashcard, Integer> {
    @Query("SELECT f FROM Flashcard f WHERE LOWER(f.frontSideContent) LIKE LOWER(CONCAT('%', :frontSideContent, '%'))")
    List<Flashcard> findByFrontSideContent(@Param("frontSideContent") String frontSideContent);
    @Query("SELECT f FROM Flashcard f WHERE LOWER(f.backSideContent) LIKE LOWER(CONCAT('%', :backSideContent, '%'))")
    List<Flashcard> findByBackSideContent(@Param("backSideContent") String backSideContent);
    @Query("SELECT f FROM Flashcard f WHERE LOWER(f.frontSideContent) LIKE LOWER(CONCAT('%', :content, '%')) OR LOWER(f.backSideContent) LIKE LOWER(CONCAT('%', :content, '%'))")
    List<Flashcard> findByContent(@Param("content") String content);
}
