package ru.aiteko.WebStore.exception;

import org.junit.jupiter.api.Test;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import java.nio.file.AccessDeniedException;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ExceptionControllerHandlerTest {

    private final ExceptionControllerHandler exceptionHandler = new ExceptionControllerHandler();

    @Test
    void testRuntimeExceptionHandler() {
        RuntimeException runtimeException = new RuntimeException("Test Runtime Exception");
        ResponseEntity<String> response = exceptionHandler.RuntimeException(runtimeException);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Internal Server Error. Contact the administrator.", response.getBody());
    }

    @Test
    void testIllegalStateExceptionHandler() {
        IllegalStateException illegalStateException = new IllegalStateException("Test Illegal State Exception");
        ResponseEntity<String> response = exceptionHandler.IllegalStateException(illegalStateException);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Illegal State. Contact the administrator.", response.getBody());
    }

    @Test
    void testIllegalArgumentExceptionHandler() {
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Test Illegal Argument Exception");
        ResponseEntity<String> response = exceptionHandler.IllegalArgumentException(illegalArgumentException);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Illegal Argument. Contact the administrator.", response.getBody());
    }

    @Test
    void testNotFoundExceptionHandler() {
        ChangeSetPersister.NotFoundException notFoundException = new ChangeSetPersister.NotFoundException();
        ResponseEntity<String> response = exceptionHandler.NotFoundException(notFoundException);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Not Found. Contact the administrator.", response.getBody());
    }

    @Test
    void testAccessDeniedExceptionHandler() {
        AccessDeniedException accessDeniedException = new AccessDeniedException("Test Access Denied Exception");
        ResponseEntity<String> response = exceptionHandler.AccessDeniedException(accessDeniedException);
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        assertEquals("Access Denied. Contact the administrator.", response.getBody());
    }

    @Test
    void testMethodArgumentTypeMismatchExceptionHandler() {
        MethodArgumentTypeMismatchException methodArgumentTypeMismatchException =
                new MethodArgumentTypeMismatchException("value", String.class, "parameter", null, null);
        ResponseEntity<String> response = exceptionHandler
                .MethodArgumentTypeMismatchException(methodArgumentTypeMismatchException);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Bad Request. Contact the administrator.", response.getBody());
    }

    @Test
    void testNoHandlerFoundExceptionHandler() {
        NoHandlerFoundException noHandlerFoundException = new NoHandlerFoundException("GET", "/path", null);
        ResponseEntity<String> response = exceptionHandler.NoHandlerFoundException(noHandlerFoundException);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Not Found. Contact the administrator.", response.getBody());
    }
}

