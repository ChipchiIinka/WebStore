package ru.aiteko.WebStore.exception.baseresponse;

import org.springframework.stereotype.Service;
import ru.aiteko.WebStore.exception.AitekoException;

@Service
public class BaseResponseService {
    public <T> ResponseWrapper<T> wrapSuccessResponse(T body){
        return ResponseWrapper
                .<T>builder()
                .success(true)
                .body(body)
                .build();
    }

    public ResponseWrapper<?> wrapErrorResponse (AitekoException exception) {
        ErrorDto error = ErrorDto.builder()
                .code(exception.getType().name())
                .title(exception.getType().getTitle())
                .text(exception.getType().getText())
                .build();

        return ResponseWrapper
                .builder()
                .success(false)
                .error(error)
                .build();
    }
}
