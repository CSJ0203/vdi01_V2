//package com.example.vdi01.dto;
//
//import com.example.vdi01.domain.questionanswer.Board;
//
//import java.time.LocalDateTime;
//
//public class BoardRequestDto {
//    public static class Save {
//        private String content;
//        private String writer;
//        private Boolean deleted;
//        private LocalDateTime createDate = LocalDateTime.now();
//        private LocalDateTime updateDate; // 수정일
//
//        //dto로 받은 Board 객체를 entity화해서 저장 Dto -> Entity
//        public Board toEntity() {
//            Board board = Board.builder()
//                    .title(title)
//                    .content(content)
//                    .writer(writer)
//                    .deleted(deleted)
//                    .createDate(createDate)
//                    .build();
//            return board;
//        }
//    }
//}

/*

낼 와서 할거
분리한거 가지고
기능별로 분리,,,, 할수잉쒕,,,*/
