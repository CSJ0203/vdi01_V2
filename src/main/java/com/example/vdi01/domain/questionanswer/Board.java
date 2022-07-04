package com.example.vdi01.domain.questionanswer;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 추가
@Getter // GETTER 메소드 추가
@Entity // 엔티티로 매핑하겠다, 엔티티 클래스는 요청과 응답에 사용되어서는 안됨
public class Board {
    @Id
    @GeneratedValue
    private Long id; // PK는 자동증가

    @Column(length = 80, nullable = false) // 해당 필드를 테이블 컬럼과 매핑, 길이는 300으로 하되 필수값이라 널여부는 false
    private String title;   // 제목

    @Column(length = 200, nullable = false)
    private String content; // 내용

    @Column(length = 50, nullable = false)
    private String writer;   // 등록자

    @Column(nullable = false)
    @Basic(optional = false)
    private Boolean deleted; // 삭제여부

    @Column
    private LocalDateTime createDate;

    @Column
    private LocalDateTime updateDate;


    //oneToMany로 불러야함

    @Builder
    public Board(String title, String content, String writer, LocalDateTime createDate, Boolean deleted){
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createDate = createDate;
        this.deleted = deleted;
    }

    public void updateBoard(String title, String content, Boolean deleted){
        this.title = title;
        this.content = content;
        this.updateDate = LocalDateTime.now();
        this.deleted = deleted;
    }

    public void deletedBoard(){
        this.deleted = true;
    }

}
