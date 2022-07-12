package com.example.vdi01.dto;

import com.example.vdi01.domain.questionanswer.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class BoardRequestDto {

    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    private String writer;
    private Boolean deleted;

    // Dto -> Entity
    public Board toEntity() {
        Board board = Board.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .deleted(deleted)
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
//        private LocalDateTime createDate = LocalDateTime.now();
        private LocalDateTime updateDate; // 수정일
    }


    @Getter
    @Builder
    @AllArgsConstructor
    public static class save {
        private String content;
        private String writer;
        private String title;
        private Boolean deleted;
//        private LocalDateTime createDate = LocalDateTime.now();
        private LocalDateTime updateDate; // 수정일
    }
}
