package com.dang.booking.validation;

import java.math.BigDecimal;
import java.time.LocalTime;

public class ValidationUtil {
    //Kiem tra chuoi
    public  static  void validateNotBlank(String value , String errorMessage){
        if(value == null || value.trim().isEmpty()){
            throw new IllegalArgumentException(errorMessage);
        }
    }
    //Kiem tra so dien thoai
    public static void validatePhoneNumber(String value, String errorMessage) {
        if (value == null || !value.matches("^[0-9]{10}$")) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    //Kiem tra rate
    public static void validateRating(BigDecimal rating, BigDecimal min, BigDecimal max, String errorMessage) {
        if (rating == null || rating.compareTo(min) <0 || rating.compareTo(max) > 0) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    //kiem tra null
    public static void validateNotNull(Object value, String errorMessage) {
        if (value == null) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
    public static void validateTime(LocalTime time, String errorMessage) {
        if (time == null) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
