package com.example.vdi01.web;

import com.example.vdi01.dto.BoardDto;
import com.example.vdi01.exception.CustomException;
import com.example.vdi01.exception.ErrorCode;
import com.example.vdi01.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    /*에러코드 테스트 ㅡㅡ*/
     @GetMapping("/test")
     public String test(){
         throw new CustomException(ErrorCode.NOT_FOUND);
     }

    /*게시글 생성*/
    @PostMapping("/boards")
    public Long save(@RequestBody final BoardDto.RequestDto dto){
        return boardService.save(dto);
    }

    /*게시글 전체 조회*/
    @GetMapping("/boards")
    public List<BoardDto.ResponseDto> findAll(){
        return boardService.findAll();
    }

    /*게시글 개별 조회*/
    @GetMapping("/boards/{id}")
    public BoardDto.ResponseDto findById(@PathVariable final Long id){
        return boardService.findById(id);
    }

    /*게시글 수정*/
    @PatchMapping("/boards/{id}")
    public Long save(@PathVariable final Long id, @RequestBody final BoardDto.RequestDto dto){
        return boardService.update(id, dto);
    }

    /*게시글 삭제*/
    @DeleteMapping("/boards/{id}")
    public Long save(@PathVariable final Long id){
        return boardService.delete(id);
    }

}
