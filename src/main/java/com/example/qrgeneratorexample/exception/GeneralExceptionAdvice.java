package com.example.qrgeneratorexample.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class GeneralExceptionAdvice extends ResponseEntityExceptionHandler {

    // handleMissingServletRequestParameter : triggers when there are missing parameters
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<String> details = new ArrayList<String>();
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getMessage() + " is not supported");
        details.add(builder.toString());

        HttpStatus httpStatus = HttpStatus.valueOf(status.value());

        Error error = new Error.ErrorBuilder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .errorDetails(details)
                .path(request.getContextPath())
                .httpStatus(httpStatus)
                .build();

        logger.error("GlobalExceptionHandler | handleHttpRequestMethodNotSupported | ex : " + ex );

        return ResponseEntity.status(status).body(error);

    }

    // handleMethodArgumentNotValid : triggers when @Valid fails
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<String> details = new ArrayList<String>();
        ex.getBindingResult().getFieldErrors().forEach(err -> {
                    String errorMessage = err.getDefaultMessage();
                    details.add(errorMessage);
                }
        );

        HttpStatus httpStatus = HttpStatus.valueOf(status.value());

        Error error = new Error.ErrorBuilder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .errorDetails(details)
                .path(request.getContextPath())
                .httpStatus(httpStatus)
                .build();

        logger.error("GlobalExceptionHandler | handleMethodArgumentNotValid | ex : " + ex );

        return new ResponseEntity<>(error, headers, status);
    }

    // handleMissingServletRequestParameter : triggers when there are missing parameters
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {


        List<String> details = new ArrayList<String>();
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getParameterName());
        details.add(builder.toString());

        HttpStatus httpStatus = HttpStatus.valueOf(status.value());

        Error error = new Error.ErrorBuilder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .errorDetails(details)
                .path(request.getContextPath())
                .httpStatus(httpStatus)
                .build();

        logger.error("GlobalExceptionHandler | handleMissingServletRequestParameter | ex : " + ex );

        return ResponseEntity.status(status).body(error);

    }

    // handleHttpMessageNotReadable : triggers when the JSON is malformed
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<String> details = new ArrayList<String>();
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getMessage());
        details.add(builder.toString());

        HttpStatus httpStatus = HttpStatus.valueOf(status.value());

        Error error = new Error.ErrorBuilder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .errorDetails(details)
                .path(request.getContextPath())
                .httpStatus(httpStatus)
                .build();

        logger.error("GlobalExceptionHandler | handleHttpMessageNotReadable | ex : " + ex );

        return ResponseEntity.status(status).body(error);

    }

}
