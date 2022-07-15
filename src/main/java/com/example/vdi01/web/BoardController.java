package com.example.vdi01.web;

import com.example.vdi01.dto.*;
import com.example.vdi01.exception.ApiException;
import com.example.vdi01.exception.ExceptionEnum;
import com.example.vdi01.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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

    /**
     * 게시글 생성
     * @param dto
     * @return
     */
    @PostMapping
    public Long postBoard(@RequestBody @Valid final BoardRequestDto dto){
        //ResponseBody로 들어오는 객체에 대한 검증 수행
        return boardService.save(dto);
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
        return boardService.findAllKeyword(keyword, type, pageable);
    }*/

    /**
     * 게시글 전체 조회
     * @param pageable
     * @return
     */
    @GetMapping("findAll")
//    public List<BoardResponseDto> 1(String keyword, @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
//    public List<BoardResponseDto> findAllPage1(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
    public ResponseEntity<List<BoardResponseDto>> findAllPage1(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        // page = 현재펭지, size = 보여줄 게시물수, sort = 페이징 조건, direction =  정렬
        // todo RepsonseEntity로 반환타입 바꾸고,,
//        return boardService.findAll(pageable);
        return ResponseEntity.ok().body(boardService.findAll(pageable));
    }

    @GetMapping
    public List<BoardResponseDto> findAll2(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){

        // page = 현재펭지, size = 보여줄 게시물수, sort = 페이징 조건, direction =  정렬
        return boardService.findAll(pageable);
    }

    /**
     * findAllPage3 변경
     * @param pageable
     * @return
     */
    @GetMapping("queryDsl")
    public List<BoardResponseDto> findAllPage(BoardSearchDto boardSearchDto, @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){

        // page = 현재펭지, size = 보여줄 게시물수, sort = 페이징 조건, direction =  정렬

        return boardService.findAllSearch(boardSearchDto, pageable);
    }

    /**
     * queryDsl list로 제대로 뽑아짐,
     * @param type
     * @param boardSearchDto
     * @param pageable
     * @return
     */
    @GetMapping("queryDsl2")
    public List<BoardResponseDto> findAllPage(String type, BoardSearchDto boardSearchDto, @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){

        // page = 현재펭지, size = 보여줄 게시물수, sort = 페이징 조건, direction =  정렬

        return boardService.findAllSearch2(type, boardSearchDto, pageable);
    }

    /**
     *
     * todo 숙제 하는즁~
     * @param boardSearchDto
     * @param pageable
     * @return
     */
    @GetMapping("queryDsl3")
    public List<BoardResponseDto> findAllPage2(BoardSearchDto boardSearchDto, @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){

        // page = 현재펭지, size = 보여줄 게시물수, sort = 페이징 조건, direction =  정렬
        // todo type없애고 BoardsearchDto 안에서 type이랑 정렬 받음

        return boardService.findAllSearch3(boardSearchDto, pageable);
    }



    /*게시글 개별 조회*/
/*    @GetMapping("/{id}")
    public BoardDto.ResponseDto findById(@PathVariable final Long id){
        return boardService.findById(id);
    }*/

    /**
     * 게시글 조회
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public BoardResponseDto findById(@PathVariable final Long id){
            // todo 반환타입 바꾸기
/*        BoardResponseDto dto = boardService.findById(id);
        List<CommentDto.Response> comments = dto.getComments();*/

         return boardService.findById(id);
    }

    /**
     * 게시글 수정
     * @param id
     * @param dto
     * @return
     */
    @PutMapping ("/{id}")
    public Long putBoard(@PathVariable final Long id, @RequestBody final BoardRequestDto dto){
        return boardService.update(id, dto);
        // todo Patch -> PUt 완료
        // todo save -> putBoard 완료
        // todo wrapper으로 감싸서 넣어라
    }

    /**
     * 게시글 삭제
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Long deleteBoard(@PathVariable final Long id){
        return boardService.delete(id);
    }

    /*답글 등록 ver1*/
     /*@PostMapping("/{boardId}/comment")
    public void save(@PathVariable Long boardId,
                     @RequestBody Comment comment){
         boardService.saveComment(boardId, comment);
     }*/

    /**
     * 답글 등록
     * @param boardId
     * @param dto
     * @return
     */
    @PostMapping("/{boardId}/comments")
    public Long postComment(@PathVariable Long boardId,
                            @RequestBody CommentDto.Request dto){

        return boardService.saveComment(boardId, dto);
    }


    /**
     * 답글 수정
     * @param commentId
     * @param dto
     * @return
     */
    @PutMapping("/{boardId}/comments/{commentId}")
    public Long save(@PathVariable Long boardId,
                     @PathVariable Long commentId,
                     @RequestBody CommentDto.Request dto){
        return boardService.updateComment(boardId, commentId, dto);
    }

}
