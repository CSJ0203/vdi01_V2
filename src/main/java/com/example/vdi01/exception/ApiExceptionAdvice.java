package com.example.vdi01.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;

@RestControllerAdvice // Controller + ResponseBody == 컨트롤러이면서 ResponseBody를 통해 객체 리턴 가능
                     // 단순 예외 처리만 하고 싶다면 Controller 사용, 응답으로 객체 리턴해야 하면 RestControllerAdvice 사용
public class ApiExceptionAdvice {
    @ExceptionHandler({ApiException.class})
    public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest request, final ApiException e) {
        // 내가 만든 ApiExceptionEntity 형식으로 예외 정보 Response
        return ResponseEntity
                .status(ExceptionEnum.OK.getStatus())
                .body(ApiExceptionEntity.builder()
                        .status(e.getError().getStatus())
                        .errorCode(e.getError().getCode())
                        .errorMessage(e.getError().getMessage())
                        .build());
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest request, final RuntimeException e) {
        e.printStackTrace();
        return ResponseEntity
                .status(ExceptionEnum.OK.getStatus())
                .body(ApiExceptionEntity.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorCode(ExceptionEnum.BAD_REQUEST.getCode())
                        .errorMessage(e.getMessage())
                        .build());
    }

    @ExceptionHandler({NoHandlerFoundException.class})
    public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest request, final NoHandlerFoundException e) {
        e.printStackTrace();
        return ResponseEntity
                .status(ExceptionEnum.OK.getStatus())
                .body(ApiExceptionEntity.builder()
                        .status(HttpStatus.NOT_FOUND)
                        .errorCode(ExceptionEnum.NOT_FOUND.getCode())
                        .errorMessage(e.getMessage())
                        .build());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest request, final Exception e) {
        e.printStackTrace();
        return ResponseEntity
                .status(ExceptionEnum.OK.getStatus())
                .body(ApiExceptionEntity.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .errorCode(ExceptionEnum.INTERNAL_SERVER_ERROR.getCode())
                        .errorMessage(e.getMessage())
                        .build());
    }
}

// todo 공통된 기능은 모듈(객체 or 메소드)로 뺴자 of를 쓰든~,,,