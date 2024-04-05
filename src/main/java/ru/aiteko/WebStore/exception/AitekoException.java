package ru.aiteko.WebStore.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AitekoException extends RuntimeException{
    private final ErrorType type;

    public AitekoException(ErrorType type, String massage){
        super(massage);
        this.type = type;
    }

    public AitekoException(ErrorType type, String massage, Throwable throwable){
        super(massage, throwable);
        this.type = type;
    }

    public AitekoException(ErrorType type, Throwable throwable){
        super(throwable);
        this.type = type;
    }
}
