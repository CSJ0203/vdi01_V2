package com.example.vdi01.domain.questionanswer;

import com.example.vdi01.dto.BoardResponseDto;
import com.example.vdi01.dto.BoardSearchDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardRepositoryCustom {
//    List<BoardResponseDto> findAllByBoardSearch(BoardSearchDto boardSearchDto);
    List<Board> findAllByBoardSearch(BoardSearchDto boardSearchDto, Pageable pageable);
}
