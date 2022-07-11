package com.example.vdi01.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public class ApiExceptionEntity {

    private HttpStatus status;
    private String errorCode;
    private String errorMessage;

    @Builder
    public ApiExceptionEntity(HttpStatus status, String errorCode, String errorMessage){
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
    //todo HttpStauts Response를 어떻게 주는지(응답하는지)

    // of || static 메소드
}