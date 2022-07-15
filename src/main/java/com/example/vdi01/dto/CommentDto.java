package com.example.vdi01.dto;

import com.example.vdi01.domain.questionanswer.Board;
import com.example.vdi01.domain.questionanswer.Comment;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class CommentDto {

    @Data
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Request{
        private Long id;
        private String comment;
        private String writer;

        public Comment toEntity(){
            Comment comment1 = Comment.builder()
                    .comment(comment)
                    .build();

            return comment1;
        }
    }

    public static class Response{
        private Long id;
        private String comment; // dto에서는 로그안이이디, 이름만 넣음
        private String writer;  // todo user엔티티(로그안아이디(객체), 이름, 이메일주소, 작성일) 만들어서 dto로 넣기 개인정보는 빼주세요
        private Board board;
        private LocalDateTime createDate;

        public Response(Comment entity){
            this.id = entity.getId();
            this.comment = entity.getComment();
            this.createDate = entity.getCreateDate();
        }
    }
}
