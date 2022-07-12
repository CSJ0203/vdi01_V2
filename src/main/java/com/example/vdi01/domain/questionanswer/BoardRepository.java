package com.example.vdi01.domain.questionanswer;

import com.example.vdi01.dto.BoardRequestDto;
import com.example.vdi01.dto.BoardResponseDto;
import com.example.vdi01.dto.BoardSearchDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;
@Repository
public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom { // Entity클래스, PK타입
   /*전체목록조회*/
//     @Query("select b.title, b.writer, b.crea~~~ from Board b")

//    @Query("select b from Board b")
//    @Query("select new com.example.vdi01.dto.BoardResponseDto(b.title, b.writer, b.createDate) from Board b")
//    @Query("select new com.example.vdi01.dto.BoardResponseDto(b.title, b.writer, b.createDate) from Board b where b.title = :#{#keyword}")
/*
    @Query("select new com.example.vdi01.dto.BoardResponseDto(b.title, b.writer, b.createDate) from Board b where b.writer like %:keyword% or b.title like %:keyword%")
    List<BoardResponseDto> findAllByBoard(String keyword, Pageable pageable);
*/
    @Query("select new com.example.vdi01.dto.BoardResponseDto(b.title, b.writer, b.createDate) from Board b")
    List<BoardResponseDto> findAllByBoard(Pageable pageable);

    // todo dto를 파라미터로 받음


/*    @Query("select b.id, b.title, b.content, b.writer, b.createDate, b.updateDate from Board b where b.writer like %:keyword%")
    List<BoardResponseDto> findAllByBoardWriter(String keyword, Pageable pageable);

    @Query("select b.id, b.title, b.content, b.writer, b.createDate, b.updateDate from Board b where b.title like %:keyword%")
    List<BoardResponseDto> findAllByBoardTitle(String keyword, Pageable pageable);*/



     /*public List<BoardResponseDto> findByBoardSearch(BoardSearchDto boardSearchDto){

     }*/

    List<Board> findAllByBoardSearch(BoardSearchDto boardSearchDto, Pageable pageable);


}
