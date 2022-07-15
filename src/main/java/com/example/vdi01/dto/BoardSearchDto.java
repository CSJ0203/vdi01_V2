package com.example.vdi01.dto;

import lombok.Data;

@Data
public class BoardSearchDto {

    // todo type을 받는다
    private String type;
    private String orderType;
    private String writer;
    private String title;


    public BoardSearchDto(String writer, String title, String type, String orderType){
        this.writer = writer;
        this.title = title;
        this.type = type;
        this.orderType = orderType;
    }
}
