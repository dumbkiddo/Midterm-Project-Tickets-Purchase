package kz.iitu.midterm.exception;

import kz.iitu.midterm.entity.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Component
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<ErrorResponse> handleNullPointerException(NullPointerException e) {
        log.error("Exception handled: "+ e.getMessage(), e);
        ErrorResponse errorResponse = new ErrorResponse("NullPointerException", e.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ClassNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleClassNotFoundException(ClassNotFoundException e) {
        log.error("Exception handled: "+ e.getMessage(), e);
        ErrorResponse errorResponse = new ErrorResponse("ClassNotFoundException", e.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleAnyException(Exception e) {
        log.error("Exception handled: "+ e.getMessage(), e);
        ErrorResponse errorResponse = new ErrorResponse("ERROR",
                "System error. Please, try to type the request again.");

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}