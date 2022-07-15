package com.example.vdi01.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
@NoArgsConstructor
public class ApiExceptionEntity<T> { // todo 범용 반환 Entity로 이름 바꾸기

    private HttpStatus status;

    private T data;     //todo T data 타입으로 받음 처리
    private String errorCode;
    private String errorMessage;

    @Builder
    public ApiExceptionEntity(HttpStatus status, String errorCode, String errorMessage){
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    // 20220714 S
    public ApiExceptionEntity(ExceptionEnum code) {
        this.status = code.getStatus();
        this.errorCode = code.getCode();
        this.errorMessage = code.getMessage();

    }

    public static ApiExceptionEntity of(ExceptionEnum code){
        return new ApiExceptionEntity(code);
    }
    // 20220714 E

}