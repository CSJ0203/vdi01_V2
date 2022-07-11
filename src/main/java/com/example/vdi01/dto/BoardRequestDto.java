package com.example.vdi01.dto;

import com.example.vdi01.domain.questionanswer.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class BoardRequestDto {

    private Long id;
    private String title;
    private String content;
    private String writer;
    private Boolean deleted;
    private LocalDateTime createDate = LocalDateTime.now();
    private LocalDateTime updateDate;

    // Dto -> Entity
    public Board toEntity() {
        Board board = Board.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .deleted(deleted)
                .createDate(createDate)
                .build();
        return board;
    }

    @Getter
    @AllArgsConstructor
    public static class List{
        private String title;
        private String writer;
        private LocalDateTime createDate = LocalDateTime.now();
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Detail {
        private String content;
        private String writer;
        private String title;
        private Boolean deleted;
        private LocalDateTime createDate = LocalDateTime.now();
        private LocalDateTime updateDate; // 수정일
    }
}
