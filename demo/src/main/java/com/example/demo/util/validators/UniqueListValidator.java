package com.example.demo.util.validators;

import com.example.demo.util.annotations.UniqueList;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UniqueListValidator implements ConstraintValidator<UniqueList, List<?>> {

    @Override
    public void initialize(UniqueList constraintAnnotation) {
    }

    @Override
    public boolean isValid(List<?> list, ConstraintValidatorContext context) {
        if (list == null) {
            return true;
        }
        Set<Object> set = new HashSet<>(list);
        return set.size() == list.size();
    }
}
