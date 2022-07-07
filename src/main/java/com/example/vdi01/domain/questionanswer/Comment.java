package com.example.vdi01.domain.questionanswer;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 추가
@Getter
@Entity
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false) // 이 컬럼을 TEXT 타입으로 지정
    private String comment; // 댓글내용

    @Column
    private LocalDateTime createDate;

    @Column
    private LocalDateTime updateDate;

    @ManyToOne  // 게시글 하나당 댓글 많음
    @JoinColumn(name = "board_id") // 왜래키 매핑, name 속성에는 매핑할 왜래키 이름 지정, board의 id를 외래키로 가짐, 필드명 + _ + 참조하는 테이블의 기본키
    private Board board; // 게시글

    @Builder
    public Comment(String comment, LocalDateTime createDate, Board board){
        this.comment = comment;
        this.createDate = createDate;
        this.board = board;
    }

    public void save(Board board){
        this.board = board;
    }

    /*댓글수정*/
    public void update(String comment){
        this.comment = comment;
        this.updateDate = LocalDateTime.now();
    }

}
