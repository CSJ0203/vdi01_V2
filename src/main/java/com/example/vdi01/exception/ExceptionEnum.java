package com.example.vdi01.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum ExceptionEnum {

    OK(HttpStatus.OK, "200", "OK"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "400", "잘못된 요청입니다"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "404", "정보를 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500", "내부 서버 오류입니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private String message;

    ExceptionEnum(HttpStatus status, String code) {
        this.status = status;
        this.code = code;
    }

    ExceptionEnum(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
