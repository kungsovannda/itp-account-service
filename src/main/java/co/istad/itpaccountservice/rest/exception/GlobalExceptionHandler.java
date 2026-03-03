package co.istad.itpaccountservice.rest.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handle(Exception e){
        return ResponseEntity.status(500)
                .body(
                        Map.of(
                                "message", e.getMessage(),
                                "error", e.getClass().getSimpleName()
                        )
                );
    }
}
