package com.dang.booking.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApiProperties {

    @Value("${api.base-path}")
    private String basePath;

    public String getBasePath() {
        return basePath;
    }
}
