package com.example.vdi01.dto;

import com.example.vdi01.domain.questionanswer.Board;
import lombok.*;

import java.time.LocalDateTime;

public class BoardDto {

    @Data
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class RequestDto{
        private Long id;
        private String title;
        private String content;
        private String writer;
        private Boolean deleted;
        private LocalDateTime createDate = LocalDateTime.now();
        private LocalDateTime updateDate; // 수정일

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

        public ResponseDto(Board entity){
            this.id = entity.getId();
            this.title = entity.getTitle();
            this.content = entity.getContent();
            this.writer = entity.getWriter();
            this.deleted = false;
            this.createDate = entity.getCreateDate();
            this.updateDate = entity.getUpdateDate();

        }

    }


}

