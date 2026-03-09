package co.istad.itpaccountservice.rest.exception;

import co.istad.itpcommon.domain.exception.DomainException;
import co.istad.itpcommon.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponse> handle(DomainException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        new ErrorResponse(
                                e.getClass().getSimpleName(),
                                e.getMessage(),
                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                ZonedDateTime.now()
                        )
                );
    }


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

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handle(ResponseStatusException e){
        return ResponseEntity.status(e.getStatusCode())
                .body(
                        Map.of(
                                "message", e.getReason(),
                                "error", e.getClass().getSimpleName()
                        )
                );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handle(HttpMessageNotReadableException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        Map.of(
                                "message", e.getMessage(),
                                "error", e.getClass().getSimpleName()
                        )
                );
    }
}
