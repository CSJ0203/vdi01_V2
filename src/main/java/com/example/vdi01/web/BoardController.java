package com.example.vdi01.web;

import com.example.vdi01.dto.BoardDto;
import com.example.vdi01.dto.commentDto;
import com.example.vdi01.exception.CustomException;
import com.example.vdi01.exception.ErrorCode;
import com.example.vdi01.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    /*에러코드 테스트 ㅡㅡ*/
     @GetMapping("/test")
     public String test(){
         throw new CustomException(ErrorCode.NOT_FOUND);
     }

    /*게시글 생성*/
    @PostMapping
    public Long save(@RequestBody final BoardDto.RequestDto dto){
        return boardService.save(dto);
    }

    /*게시글 전체 조회*/
    @GetMapping
    public List<BoardDto.ResponseDto> findAll(){
        return boardService.findAll();
    }

    /*게시글 개별 조회*/
    @GetMapping("/{id}")
    public BoardDto.ResponseDto findById(@PathVariable final Long id){
        return boardService.findById(id);
    }

    /*게시글 수정*/
    @PatchMapping("/{id}")
    public Long save(@PathVariable final Long id, @RequestBody final BoardDto.RequestDto dto){
        return boardService.update(id, dto);
    }

    /*게시글 삭제*/
    @DeleteMapping("/{id}")
    public Long save(@PathVariable final Long id){
        return boardService.delete(id);
    }

    /*답글 등록 ver1*/
     /*@PostMapping("/{boardId}/comment")
    public void save(@PathVariable Long boardId,
                     @RequestBody Comment comment){
         boardService.saveComment(boardId, comment);
     }*/

    /*답글 등록 postComment*/
    @PostMapping("/{boardId}/comment")
    public Long postComment(@PathVariable Long boardId,
                            @RequestBody commentDto.Request dto){

        return boardService.saveComment(boardId, dto);
    }

    /*답글 수정*/

    /**
     *
     * @param commentId
     * @param dto
     * @return
     */
    @PutMapping("/{boardId}/comment/{commentId}")
    public Long save(@PathVariable Long boardId,
                     @PathVariable Long commentId,
                     @RequestBody commentDto.Request dto){
        return boardService.updateComment(boardId, commentId, dto);
    }

}
