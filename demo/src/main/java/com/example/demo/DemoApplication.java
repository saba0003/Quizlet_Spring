package com.example.demo;

import com.example.demo.models.Flashcard;
import com.example.demo.models.Question;
import com.example.demo.repositories.FlashcardRepository;
import com.example.demo.repositories.QuestionRepository;
import com.example.demo.util.Answer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public ServletWebServerFactory servletWebServerFactory() {
		return new TomcatServletWebServerFactory();
	}

	@Bean
	public CommandLineRunner commandLineRunner(QuestionRepository questionRepository,
											   FlashcardRepository flashcardRepository) {
		return args -> {

			Question question1 = Question.builder()
					.description("The best programming language")
					.possibleAnswers(List.of(
							new Answer("C"),
							new Answer("C++"),
							new Answer("C#"),
							new Answer("Java")
					))
					.correctAnswer(4)
					.build();

			Question question2 = Question.builder()
					.description("Some months have 31 days, and other months have 30. Then, how many months have 28 days?")
					.possibleAnswers(List.of(
							new Answer("One"),
							new Answer("Five"),
							new Answer("Seven"),
							new Answer("Twelve")
					))
					.correctAnswer(4)
					.build();

			Flashcard flashcard = Flashcard.builder()
					.frontSideContent("pluviophile")
					.backSideContent("a person who enjoys rain and rainy days, and who is fascinated by the sights, sounds, etc., of rain")
					.build();

			questionRepository.saveAll(List.of(question1, question2));
			flashcardRepository.save(flashcard);
		};
	}
}
