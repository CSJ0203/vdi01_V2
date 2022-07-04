package com.example.vdi01.exception;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.common.util.impl.Log;
import org.slf4j.Logger;
import org.springframework.boot.logging.Slf4JLoggingSystem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // Controller + ResponseBody == 컨트롤러이면서 ResponseBody를 통해 객체 리턴 가능
                    // 단순 예외 처리만 하고 싶다면 Controller 사용, 응답으로 객체 리턴해야 하면 RestControllerAdvice 사용
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class) // 에러를 잡아서 CustomerException의 클래스를 사용해 처리해주겠다
    protected ResponseEntity<ErrorResponse> handleCustomException(final CustomException e){
        // HTTP Request에 대한 응답 데이터를 포함한 클래스
        // <Type>에 해당하는 데이터와 HTTP 상태 코드 리턴
        // 내가 만든 ErrorResponse 형식으로 예외 정보 Response
//        log.error("handleCustomException: {}", e.getErrorCode());

        return ResponseEntity
                .status(e.getErrorCode().getStatus().value())
                .body(new ErrorResponse(e.getErrorCode()));
    }

/*    *//*CustomException에서 정의한 404 *//*
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException404 (final Exception e){
//        log.error("handleException: {}", e.getMessage());
        return ResponseEntity
                .status(ErrorCode.NOT_FOUND.getStatus().value())
                .body(new ErrorResponse(ErrorCode.NOT_FOUND));
    }*/


    /*
     * HTTP 500 Exception
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(final Exception e) {
        log.error("handleException: {}", e.getMessage());
        return ResponseEntity
                .status(ErrorCode.INTERNAL_SERVER_ERROR.getStatus().value())
                .body(new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR));
    }
}
