package com.example.demo.repositories;

import com.example.demo.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    @Query("SELECT q FROM Question q WHERE LOWER(q.description) LIKE LOWER(CONCAT('%', :description, '%'))")
    List<Question> findByDescription(@Param("description") String description);
    @Query("SELECT q FROM Question q JOIN q.possibleAnswers pa WHERE LOWER(pa) LIKE LOWER(CONCAT('%', :possibleAnswer, '%'))")
    List<Question> findByPossibleAnswer(@Param("possibleAnswer") String possibleAnswer);
}
