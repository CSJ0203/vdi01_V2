package com.example.vdi01.web;

import com.example.vdi01.domain.questionanswer.Board;
import com.example.vdi01.dto.*;
import com.example.vdi01.exception.ApiException;
import com.example.vdi01.exception.CustomException;
import com.example.vdi01.exception.ErrorCode;
import com.example.vdi01.exception.ExceptionEnum;
import com.example.vdi01.service.BoardService;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.TypeCache;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    /*에러코드 테스트 ㅡㅡ*/
     @GetMapping("/test")
     public String test(){
//         throw new CustomException(ErrorCode.NOT_FOUND);
         throw new ApiException(ExceptionEnum.BAD_REQUEST);
     }

    @GetMapping("/test2")
    public String test2(){
//         throw new CustomException(ErrorCode.NOT_FOUND);
        throw new ApiException(ExceptionEnum.NOT_FOUND);
    }

    /*게시글 생성*/
/*    @PostMapping
    public Long save(@RequestBody final BoardDto.RequestDto dto){
        return boardService.save(dto);
    }*/

    /*게시글 생성 2, dto 분리*/
    @PostMapping
    public Long postBoard(@RequestBody @Valid final BoardRequestDto dto){
        //ResponseBody로 들어오는 객체에 대한 검증 수행
        return boardService.save(dto);
        // todo save-> "postBoard" 완료
        // todo @valid 완료
    }

    /*게시글 전체 조회 1*/
/*    @GetMapping
    public List<BoardDto.ResponseDto> findAll(){
        return boardService.findAll();
    }*/

    /*게시글 전체 조회 2, dto 분리본 사용*/
/*    @GetMapping("/keyword/{type}")
    public List<BoardResponseDto> findAllKeyword(String keyword, @PathVariable final String type, @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
       // page = 현재펭지, size = 보여줄 게시물수, sort = 페이징 조건, direction =  정렬
        // todo 검색 구현 : 타이틀 and 글쓴이
        // todo finaAll -> findAllPage
        return boardService.findAllKeyword(keyword, type, pageable);
    }*/

    /*게시글 전체 조회 3, dto 분리본 사용, paging 사용*/
    @GetMapping("findAll")
//    public List<BoardResponseDto> findAll(String keyword, @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
    public List<BoardResponseDto> findAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        // page = 현재펭지, size = 보여줄 게시물수, sort = 페이징 조건, direction =  정렬
        // todo 검색 구현 : 타이틀 and 글쓴이
        // todo finaAll -> findAllPage
//        return boardService.findAll(keyword, pageable);
        return boardService.findAll(pageable);
    }

    @GetMapping
    public List<BoardResponseDto> findAll2(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){

        // page = 현재펭지, size = 보여줄 게시물수, sort = 페이징 조건, direction =  정렬
        // todo 검색 구현 : 타이틀 and 글쓴이
        // todo finaAll -> findAllPage
        return boardService.findAll(pageable);
    }

    /**
     * 그거뭐여,,,,
     * @param pageable
     * @return
     */
    @GetMapping("queryDsl")
    public List<Board> findAll3(BoardSearchDto boardSearchDto, @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){

        // page = 현재펭지, size = 보여줄 게시물수, sort = 페이징 조건, direction =  정렬
        // todo 검색 구현 : 타이틀 and 글쓴이
        // todo finaAll -> findAllPage
        return boardService.findAllSearch(boardSearchDto, pageable);
    }


    /*게시글 개별 조회*/
/*    @GetMapping("/{id}")
    public BoardDto.ResponseDto findById(@PathVariable final Long id){
        return boardService.findById(id);
    }*/

    /*게시글 개별 조회 2, dto 분리본 사용*/
    @GetMapping("/{id}")
    public BoardResponseDto findById(@PathVariable final Long id){

/*        BoardResponseDto dto = boardService.findById(id);
        List<CommentDto.Response> comments = dto.getComments();*/

         return boardService.findById(id);
    }

    /*게시글 수정*/
    @PatchMapping("/{id}")
    public Long save(@PathVariable final Long id, @RequestBody final BoardDto.RequestDto dto){
        return boardService.update(id, dto);
        // todo Patch -> PUt
        // todo save -> putBoard
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
                            @RequestBody CommentDto.Request dto){

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
                     @RequestBody CommentDto.Request dto){
        return boardService.updateComment(boardId, commentId, dto);
    }

}
