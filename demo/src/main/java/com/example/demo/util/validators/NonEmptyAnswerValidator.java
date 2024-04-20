package com.example.demo.util.validators;

import com.example.demo.util.Answer;
import com.example.demo.util.annotations.NonEmptyAnswer;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class NonEmptyAnswerValidator implements ConstraintValidator<NonEmptyAnswer, List<Answer>> {
    @Override
    public void initialize(NonEmptyAnswer constraintAnnotation) {
    }

    @Override
    public boolean isValid(List<Answer> answers, ConstraintValidatorContext context) {
        if (answers == null)
            return true;
        for (Answer answer : answers)
            if (answer == null || StringUtils.isEmpty(answer.getContent()))
                return false;
        return true;
    }
}
