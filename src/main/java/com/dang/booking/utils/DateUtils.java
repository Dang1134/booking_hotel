package com.dang.booking.utils;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
public class DateUtils {
    private final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public LocalDate convertStringToLocalDate(String dateString) {
        try {
            return LocalDate.parse(dateString, DATE_FORMATTER);

        } catch (DateTimeParseException e) {
            throw new RuntimeException("Cannot convert String to LocalDate");
        }
    }
}
