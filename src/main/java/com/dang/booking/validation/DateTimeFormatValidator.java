package com.dang.booking.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeFormatValidator  implements ConstraintValidator<ValidDateTimeFormat,String>{
    private String pattern;

    @Override
    public void initialize(ValidDateTimeFormat constraintAnnotation) {
       this.pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String dateStr, ConstraintValidatorContext context) {
        if (dateStr == null || dateStr.isEmpty()) {
            return false;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            LocalDate.parse(dateStr, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
