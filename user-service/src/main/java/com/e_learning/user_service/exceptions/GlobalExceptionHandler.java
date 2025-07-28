package com.e_learning.user_service.exceptions;

import com.e_learning.user_service.dto.ConstraintValidationErrorResponseDTO;
import com.e_learning.user_service.dto.ErrorResponseDTO;
import com.e_learning.user_service.dto.MethodArgumentValidationResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ConstraintValidationErrorResponseDTO> handleConstraintViolationException(
            ConstraintViolationException ex, HttpServletRequest request
    ) {
        Map<String, String> validationErrors = new HashMap<>();

        ex.getConstraintViolations().forEach(violation -> {
            String field = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            validationErrors.put(field, message);
        });

        ConstraintValidationErrorResponseDTO errorResponse = new ConstraintValidationErrorResponseDTO(
                request.getRequestURI().replace("uri=", ""),
                HttpStatus.BAD_REQUEST.value(),
                "plz provide a valid input data.",
                LocalDateTime.now(),
                validationErrors
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatusCode status, WebRequest request) {
        Map<String, String> validationErrors = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        validationErrorList.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String validationMsg = error.getDefaultMessage();
            validationErrors.put(fieldName, validationMsg);
        });

        var errorResponse = new MethodArgumentValidationResponseDTO(
                request.getDescription(false).replace("uri=", ""),
                HttpStatus.BAD_REQUEST.value(),
                "plz provide a valid input data.",
                LocalDateTime.now(),
                validationErrors
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGlobalException(
            Exception exception, WebRequest webRequest) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO();
        errorResponse.setApiPath(webRequest.getDescription(false).replace("uri=", ""));
        errorResponse.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setErrorMessage(exception.getMessage());
        errorResponse.setErrorTime(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler({
            ConflictException.class,
            NotFoundException.class
    })
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(
            RuntimeException exception, WebRequest webRequest
    ) {
        HttpStatus resolveStatus = resolveStatus(exception);
        ErrorResponseDTO errorResponse = new ErrorResponseDTO();
        errorResponse.setApiPath(webRequest.getDescription(false).replace("uri=", ""));
        errorResponse.setErrorCode(resolveStatus.value());
        errorResponse.setErrorMessage(exception.getMessage());
        errorResponse.setErrorTime(LocalDateTime.now());
        return ResponseEntity.status(resolveStatus).body(errorResponse);
    }

    private HttpStatus resolveStatus(RuntimeException ex) {
        if (ex instanceof ConflictException) return HttpStatus.CONFLICT;
        if (ex instanceof NotFoundException) return HttpStatus.NOT_FOUND;
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

}
