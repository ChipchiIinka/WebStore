package ru.aiteko.WebStore.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.aiteko.WebStore.exception.baseresponse.BaseResponseService;
import ru.aiteko.WebStore.exception.baseresponse.ResponseWrapper;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionApiHandler {
    private final BaseResponseService baseResponseService;

    @ExceptionHandler(Throwable.class)
    public ResponseWrapper<?> handleOtherException(Throwable t){
        log.error("Got exception {}, massage: {}", t.getClass(), t.getMessage());

        return baseResponseService.wrapErrorResponse(new AitekoException(ErrorType.COMMON_ERROR, t));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseWrapper<?> handleAuthException(AuthenticationException a){
        log.error("Got exception {}, massage: {}", a.getClass(), a.getMessage());

        return baseResponseService.wrapErrorResponse(new AitekoException(ErrorType.AUTH_ERROR, a));
    }

    @ExceptionHandler(AitekoException.class)
    public ResponseWrapper<?> handleLabWorkException(AitekoException exception){
        return baseResponseService.wrapErrorResponse(exception);
    }

    @ResponseStatus (HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ConstraintViolationException.class, MethodArgumentNotValidException.class})
    public ResponseWrapper<?> handleValidationException(Exception e){
        log.error("Got validation exception {}, massage: {}", e.getClass(), e.getMessage());

        return baseResponseService.wrapErrorResponse(new AitekoException(ErrorType.CLIENT_ERROR, e));
    }
}
