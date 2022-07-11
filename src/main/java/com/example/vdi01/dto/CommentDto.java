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
        private LocalDateTime createDate = LocalDateTime.now();
        private Board board;

        public Comment toEntity(){
            Comment comment1 = Comment.builder()
                    .comment(comment)
                    .board(board)
                    .createDate(createDate)
                    .build();

            return comment1;
        }
    }

    public static class Response{
        private Long id;
        private String comment;
        private String writer;
        private Board board;
        private LocalDateTime createDate;

        public Response(Comment entity){
            this.id = entity.getId();
            this.comment = entity.getComment();
            this.createDate = entity.getCreateDate();
        }
    }
}
