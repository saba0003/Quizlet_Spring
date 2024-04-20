package com.example.demo.util.annotations;

import com.example.demo.util.validators.UniqueListValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueListValidator.class)
public @interface UniqueList {
    String message() default "List must contain unique elements";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
