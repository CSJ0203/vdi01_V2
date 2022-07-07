package com.example.vdi01.dto;

import com.example.vdi01.domain.questionanswer.Board;
import lombok.*;

import java.time.LocalDateTime;

public class BoardDto {

    @Data
    public static class RequestDto{
        private Long id;
        private String title;
        private String content;
        private String writer;
        private Boolean deleted;
        private LocalDateTime createDate = LocalDateTime.now();
        private LocalDateTime updateDate; // 수정일

        //dto로 받은 Board 객체를 entity화해서 저장 Dto -> Entity
        public Board toEntity(){
            Board board = Board.builder()
                    .title(title)
                    .content(content)
                    .writer(writer)
                    .deleted(deleted)
                    .createDate(createDate)
                    .build();
            return board;
        }

        // 20220705 추가
//        @Builder
//        public RequestDto(Long id, String title, String content, LocalDateTime createDate){
//            this.id = id;
//            this.title = title;
//            this.content = content;
//            this.createDate = createDate;
//        }

//        public Request(Posts entity){
//            this.id = entity.getId();
//            this.title = entity.getTitle();
//            this.content = entity.getContent();
//            this.writer = entity.getWriter();
//            this.deleteYn = entity.getDeleteYn();
//            this.createdDate = entity.getCreatedDate();
//            this.modifiedDate = entity.getModifiedDate();
//        }

    }
    
    @Getter
    public static class ResponseDto { // response 객체 생성은 필수적임, 엔티티 클래스가 사용되면 안됨
        private Long id;
        private String title;
        private String content;
        private String writer;
        private Boolean deleted;
        private LocalDateTime createDate;
        private LocalDateTime updateDate; // 수정일

        /*Entity -> Dto 상세, 리스트 두개로 쪼개기*/

        //가장 기존의 것,,
        public ResponseDto(Board board){
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.writer = board.getWriter();
            this.createDate = board.getCreateDate();
        }

//        public ResponseDto(String title, String content, LocalDateTime createDate){
//            this.title = title;
//            this.content = content;
//            this.createDate = createDate;
//        }
//
//        public ResponseDto(Long id, String content, String title, String writer, LocalDateTime createDate){
//            this.id = id;
//            this.content = content;
//            this.title = title;
//            this.writer = writer;
//            this.createDate = createDate;
//        }

        // 20220705 추가
        @Builder
        public ResponseDto(Long id, String title, String content, LocalDateTime createDate){
            this.id = id;
            this.title = title;
            this.content = content;
            this.createDate = createDate;
        }


    }


}

