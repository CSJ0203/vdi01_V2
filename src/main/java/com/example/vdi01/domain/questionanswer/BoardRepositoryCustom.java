package com.example.vdi01.domain.questionanswer;

import com.example.vdi01.dto.BoardResponseDto;
import com.example.vdi01.dto.BoardSearchDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardRepositoryCustom {
//    List<BoardResponseDto> findAllByBoardSearch(BoardSearchDto boardSearchDto);
    List<BoardResponseDto> findAllByBoardSearch(BoardSearchDto boardSearchDto, Pageable pageable);

    List<BoardResponseDto> findAllByBoardSearchLike(BoardSearchDto boardSearchDto, Pageable pageable);
    List<BoardResponseDto> findAllByBoardSearchEq(BoardSearchDto boardSearchDto, Pageable pageable);

    //   List<BoardResponseDto> findAllByBoardLike(String type, BoardSearchDto boardSearchDto, Pageable pageable);
    // List<BoardResponseDto> findAllByBoardEq(String type, BoardSearchDto boardSearchDto, Pageable pageable);



}
