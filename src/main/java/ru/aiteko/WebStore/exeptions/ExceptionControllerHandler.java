package ru.aiteko.WebStore.exeptions;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import java.nio.file.AccessDeniedException;

/*
 * Перехватчик исключений внутри сервиса
 */

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionControllerHandler{

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> RuntimeException(RuntimeException ex) {
        log.error("Runtime exception", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error. Contact the administrator.");
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> IllegalStateException(IllegalStateException ex) {
        log.error("Illegal state", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Illegal State. Contact the administrator.");
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<String> AccessDeniedException(AccessDeniedException ex) {
        log.warn("Access denied", ex);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied. Contact the administrator.");
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        log.warn("Method argument type mismatch", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request. Contact the administrator.");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> IllegalArgumentException(IllegalArgumentException ex) {
        log.warn("Illegal argument", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Illegal Argument. Contact the administrator.");
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> NoHandlerFoundException(NoHandlerFoundException ex) {
        log.warn("No handler found for this request", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found. Contact the administrator.");
    }

    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> NotFoundException(ChangeSetPersister.NotFoundException ex) {
        log.warn("Resource not found", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found. Contact the administrator.");
    }
}
