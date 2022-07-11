package com.example.vdi01.dto;

import com.example.vdi01.domain.questionanswer.Board;
import com.example.vdi01.domain.questionanswer.Comment;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class BoardResponseDto {

    private long id;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createDate = LocalDateTime.now();

    private LocalDateTime updateDate;

    private List<CommentDto.Response> comments;

    // Entity -> Dto
    public BoardResponseDto (Board board){
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.writer = board.getWriter();
        this.createDate = board.getCreateDate();
        this.comments = board.getComments().stream().map(CommentDto.Response::new).collect(Collectors.toList());
    }
    @Builder
    public BoardResponseDto (String title, String writer, LocalDateTime createDate){
        this.title = title;
        this.createDate = createDate;
        this.writer = writer;
    }

    @Builder
    public BoardResponseDto (String title, String writer,String content, LocalDateTime createDate){
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createDate = createDate;
    }

}
