package com.ciber.calculator.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestController
@ControllerAdvice
public class ExceptionManager {
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleValidationException(MethodArgumentTypeMismatchException ex) {
        String parameterName = ex.getName();
        String errorMessage = "The type of the '" + parameterName + "' parameter is not valid for this request.";

        return ResponseEntity.badRequest().body(errorMessage);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleMissingParameterException(MissingServletRequestParameterException ex) {
        String parameterName = ex.getParameterName();
        String errorMessage = "The required parameter '" + parameterName + "' is not present in the request.";

        return ResponseEntity.badRequest().body(errorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        
        StringBuilder errorMessage = new StringBuilder();
        for (FieldError fieldError : fieldErrors) {
            errorMessage.append(fieldError.getDefaultMessage()).append("; ");
        }

        return ResponseEntity.badRequest().body("Validation error: " + errorMessage.toString());
    }
}
