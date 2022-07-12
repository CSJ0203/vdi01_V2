package com.example.vdi01.dto;

import lombok.Data;

@Data
public class BoardSearchDto {
    private String writer;
    private String title;

    public BoardSearchDto(String writer, String title){
        this.writer = writer;
        this.title = title;
    }
}
