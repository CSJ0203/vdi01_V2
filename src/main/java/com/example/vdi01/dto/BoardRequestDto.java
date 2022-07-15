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

}
