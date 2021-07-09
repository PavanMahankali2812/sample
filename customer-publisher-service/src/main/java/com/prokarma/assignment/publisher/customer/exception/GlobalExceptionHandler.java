package com.prokarma.assignment.publisher.customer.exception;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prokarma.assignment.publisher.customer.domain.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public static final String ERROR_STATUS = "error";

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> noHandlerFound(NoHandlerFoundException ex,
            HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(ERROR_STATUS);
        errorResponse.setMessage(ex.getLocalizedMessage());
        errorResponse.setErrorType(ex.getClass().getSimpleName());
        logger.error("NoHandlerFoundException : {}", errorResponse);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ResourceAccessException.class, ApplicationRuntimeException.class})
    public final ResponseEntity<ErrorResponse> handleException(ResourceAccessException ex,
            HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(ERROR_STATUS);
        errorResponse.setMessage(ex.getLocalizedMessage());
        errorResponse.setErrorType(ex.getClass().getSimpleName());
        logger.error("ApplicationRuntimeException : {}", errorResponse);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, MissingRequestHeaderException.class})
    public final ResponseEntity<ErrorResponse> requestValidationException(Exception ex,
            HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(ERROR_STATUS);
        errorResponse.setMessage(ex.getLocalizedMessage());
        errorResponse.setErrorType(ex.getClass().getSimpleName());
        logger.error("MissingRequestHeaderException : {}", errorResponse);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RequestValidationException.class)
    public final ResponseEntity<ErrorResponse> getRequestValidationException(
            RequestValidationException ex, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(ERROR_STATUS);
        errorResponse.setMessage(ex.getLocalizedMessage());
        errorResponse.setErrorType(ex.getClass().getSimpleName());
        logger.error("RequestValidationException : {}", errorResponse);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgumentNotValidException(
            MethodArgumentNotValidException ex, HttpServletRequest request)
            throws JsonProcessingException {
        BindingResult result = ex.getBindingResult();
        List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(ERROR_STATUS);
        errorResponse.setMessage(ex.getLocalizedMessage());
        errorResponse.setErrorType(ex.getClass().getSimpleName());
        logger.error("MethodArgumentNotValidException : {}", errorResponse);
        return new ResponseEntity<>(processFieldErrors(fieldErrors, errorResponse),
                HttpStatus.BAD_REQUEST);
    }

    private ErrorResponse processFieldErrors(
            List<org.springframework.validation.FieldError> fieldErrors,
            ErrorResponse errorResponse) throws JsonProcessingException {
        errorResponse.setStatus(ERROR_STATUS);
        FieldErrorResponse fieldErrorResponse = new FieldErrorResponse();

        Map<String, TreeSet<String>> fieldValidationError = new TreeMap<>();
        for (org.springframework.validation.FieldError fieldError : fieldErrors) {
            if (fieldValidationError.containsKey(fieldError.getField())) {
                TreeSet<String> error = fieldValidationError.get(fieldError.getField());
                error.add(fieldError.getDefaultMessage());
                fieldValidationError.put(fieldError.getField(), error);
            } else {
                TreeSet<String> error = new TreeSet<>();
                error.add(fieldError.getDefaultMessage());
                fieldValidationError.put(fieldError.getField(), error);
            }
        }
        fieldErrorResponse.setModelState(fieldValidationError);
        errorResponse.setMessage(new ObjectMapper().writeValueAsString(fieldValidationError));

        return errorResponse;
    }
}
