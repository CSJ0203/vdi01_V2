package com.example.vdi01.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    /*400 BAD_REQUEST*/
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다"),

    /*404 NOT_FOUND*/
    NOT_FOUND(HttpStatus.OK, "게시글 정보를 찾을 수 없습니다."),

    /*500 INTERNAL_SERVER_ERROR*/
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버 오류입니다."),
    ;

    // ERROR 코드 추가 ^^
    private final HttpStatus status;
    private final String message;
}
