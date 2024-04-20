package com.example.demo.util.annotations;

import com.example.demo.util.validators.NonEmptyAnswerValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NonEmptyAnswerValidator.class)
public @interface NonEmptyAnswer {
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
