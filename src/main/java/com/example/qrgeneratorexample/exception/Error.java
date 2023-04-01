package com.example.qrgeneratorexample.exception;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record Error (
        LocalDateTime timestamp,
        int status,
        String path,
        HttpStatus httpStatus,
        List<String> errorDetails
) { }
