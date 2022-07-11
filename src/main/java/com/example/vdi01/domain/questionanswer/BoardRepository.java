package com.example.vdi01.domain.questionanswer;

import com.example.vdi01.dto.BoardRequestDto;
import com.example.vdi01.dto.BoardResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;
@Repository
public interface BoardRepository extends JpaRepository<Board, Long> { // Entity클래스, PK타입
   /*전체목록조회*/
//     @Query("select b.title, b.writer, b.crea~~~ from Board b")

//    @Query("select b from Board b")
    @Query("select new com.example.vdi01.dto.BoardResponseDto(b.title, b.writer, b.createDate) from Board b")
     List<BoardResponseDto> findAllByBoard(Pageable pageable);

    //@Query("select new com.example.vdi01.dto.BoardResponseDto(b.title, b.content, b.createDate, b.writer) from Board b")

}
