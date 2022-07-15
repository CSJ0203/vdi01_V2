package com.example.vdi01.service;

import com.example.vdi01.domain.questionanswer.Board;
import com.example.vdi01.domain.questionanswer.BoardRepository;
import com.example.vdi01.domain.questionanswer.Comment;
import com.example.vdi01.domain.questionanswer.CommentRepository;
import com.example.vdi01.dto.*;
import com.example.vdi01.exception.ApiException;
import com.example.vdi01.exception.ExceptionEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;

@Service
// @RequestMapping("/boards")
@RequiredArgsConstructor // final로 선언된 모든 멤버에 대한 생성자 만들어준댜
public class BoardService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    /*게시글 생성*/
/*    @Transactional
    public Long save(final BoardDto.RequestDto dto){

        Board board = boardRepository.save(dto.toEntity()); // 객체명 따라서
        return board.getId();
    }*/

    /*게시글 생성2 dto 분리*/
    @Transactional
    public Long save(final BoardRequestDto dto){

        Board board = boardRepository.save(dto.toEntity()); // 객체명 따라서
        return board.getId();
    }


    /*게시글 리스트 1*/
/*    @Transactional
    public List<BoardDto.ResponseDto> findAll(){
        // 화면에서 받아올땐 Ajax대신 fetch
        // Ajax보다 API를 간단하게 호출한다는데 진짜일까 ㅇㅅㅇ
        List<Board> boards = boardRepository.findAll(); // 리스트 뽑아서 엔티티로 받은 걸 컨트롤러로 던지기 위해 dto로 변경해야함

        // java 8부터 생긴 Stream
//        return list.stream() //boardRepository 뒤져서 나온 결과 list의 Stream을
//                .map(BoardDto.ResponseDto::new) // map을 통해 BoardDto.ResponseDto로 변환해서, 참고로 map filter sorted 있음 노션 정리 ㄱ
//                .collect(Collectors.toList()); // List로 반환

//        List<BoardDto.ResponseDto> resultList = list.stream().map(list -> model)

        List<BoardDto.ResponseDto> boardDtos = new ArrayList<>(); // dto타입의 리스트 맨들기

        // 까였음
        for (Board board: boards) {
            BoardDto.ResponseDto dto = BoardDto.ResponseDto.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .createDate(board.getCreateDate())
                    .build();

            boardDtos.add(dto);
        }
        return boardDtos;
    }*/

    /*게시글 조회 2*/
    @Transactional
//    public List<BoardResponseDto> findAll(String keyword, Pageable pageable){
    public List<BoardResponseDto> findAll(Pageable pageable){

//        Sort sort = Sort.by(Sort.Direction.DESC, "id", "createDate"); // id, 생성날짜순

//        List<BoardResponseDto> boards = boardRepository.findAllByBoard(keyword, pageable);
        List<BoardResponseDto> boards = boardRepository.findAllByBoard(pageable);
        List<BoardResponseDto> boardDtos = new ArrayList<>();


        for (BoardResponseDto boardDto:boards) {
            BoardResponseDto dto = BoardResponseDto.builder()
                    .title(boardDto.getTitle())
                    .writer(boardDto.getWriter())
                    .createDate(boardDto.getCreateDate())
                    .build();

            boardDtos.add(dto);
        }


        return boardDtos;
//       return boardRepository.findAll().stream().map(BoardResponseDto::new).collect(Collectors.toList());
    }


/*    @Transactional
    public List<BoardResponseDto> findAllKeyword(String keyword, String type, Pageable pageable){

        List<BoardResponseDto> boards = new ArrayList<>();
        List<BoardResponseDto> boardDtos = new ArrayList<>();

        if(!keyword.isEmpty() && !type.isEmpty()){
            if(type.equals("title")){
                boards = boardRepository.findAllByBoardTitle(keyword, pageable);
            }
            else if(type.equals("writer")){
                boards = boardRepository.findAllByBoardWriter(keyword, pageable);
            }
        }
        *//*else if(keyword.isEmpty() && type.isEmpty()){
            boards = boardRepository.findAllByBoard(pageable);
        }*//*

        for (BoardResponseDto boardDto:boards) {
            BoardResponseDto dto = BoardResponseDto.builder()
                    .title(boardDto.getTitle())
                    .writer(boardDto.getWriter())
                    .createDate(boardDto.getCreateDate())
                    .build();

            boardDtos.add(dto);
        }


        return boardDtos;
    }*/


    // findAllByBoardSearch
    @Transactional
    public List<BoardResponseDto> findAllSearch(BoardSearchDto boardSearchDto, Pageable pageable){

        List<BoardResponseDto> boards = boardRepository.findAllByBoardSearch(boardSearchDto, pageable);
        List<BoardResponseDto> boardDtos = new ArrayList<>();


        for (BoardResponseDto boardDto:boards) {
            BoardResponseDto dto = BoardResponseDto.builder()
                    .title(boardDto.getTitle())
                    .writer(boardDto.getWriter())
                    .createDate(boardDto.getCreateDate())
                    .build();

            boardDtos.add(dto);
        }


        return boardDtos;
    }


    @Transactional
    public List<BoardResponseDto> findAllSearch2(String type, BoardSearchDto boardSearchDto, Pageable pageable){

        List<BoardResponseDto> boards = new ArrayList<>();

        if("like".equals(type)){
            boards = boardRepository.findAllByBoardSearchLike(boardSearchDto, pageable);
        } if("eq".equals(type)){
            boards = boardRepository.findAllByBoardSearchEq(boardSearchDto,pageable);
        }

        List<BoardResponseDto> boardDtos = new ArrayList<>();


        for (BoardResponseDto boardDto:boards) {
            BoardResponseDto dto = BoardResponseDto.builder()
                    .title(boardDto.getTitle())
                    .writer(boardDto.getWriter())
                    .createDate(boardDto.getCreateDate())
                    .build();

            boardDtos.add(dto);
        }


        return boardDtos;
    }


    @Transactional
    public List<BoardResponseDto> findAllSearch3(BoardSearchDto boardSearchDto, Pageable pageable){

        List<BoardResponseDto> boards = new ArrayList<>();

/*        if("like".equals(type)){
            boards = boardRepository.findAllByBoardSearchLike(boardSearchDto, pageable);
        } if("eq".equals(type)){
            boards = boardRepository.findAllByBoardSearchEq(boardSearchDto,pageable);
        }*/

        if("like".equals(boardSearchDto.getType())){
            boards = boardRepository.findAllByBoardSearchLike(boardSearchDto, pageable);
        }else if("eq".equals(boardSearchDto.getType())){
            boards = boardRepository.findAllByBoardSearchEq(boardSearchDto,pageable);
        }

        List<BoardResponseDto> boardDtos = new ArrayList<>();


        for (BoardResponseDto boardDto:boards) {
            BoardResponseDto dto = BoardResponseDto.builder()
                    .title(boardDto.getTitle())
                    .writer(boardDto.getWriter())
                    .createDate(boardDto.getCreateDate())
                    .build();

            boardDtos.add(dto);
        }


        return boardDtos;
    }



    /*게시글 상세*/
/*    @Transactional
    public BoardDto.ResponseDto findById(Long id){

        Board board = boardRepository.findById(id)
                .orElseThrow(()-> new CustomException(ErrorCode.NOT_FOUND)); // 게시글 없음

        return new BoardDto.ResponseDto(board);

    }*/


    @Transactional
    public BoardResponseDto findById(Long id){

        Board board = boardRepository.findById(id)
                .orElseThrow(()-> new ApiException(ExceptionEnum.NOT_FOUND));

        return new BoardResponseDto(board);

    }

    /*게시글 수정*/
    @Transactional
    public Long update(Long id, BoardRequestDto dto){
        Board board = boardRepository.findById(id)
                .orElseThrow(()-> new ApiException(ExceptionEnum.NOT_FOUND));

        board.updateBoard(dto.getTitle(), dto.getContent(), dto.getDeleted());
        // UPDATE 쿼리 실행 로직 없음, but 해당 메서드가 실행 종료되면 update 쿼리 자동 실행
        // 영속성 컨텍스트 개념 이해하기
        // 영속성 컨텍스트? == Entity를 영구히 저장하는 환경 의미, App과 DB 사이에서 객체 보관하는 가상 영역
        // 엔티티 매니저는 Entity가 생성되거나 Entity를 조회하는 시점에서 영속성 컨텍스트에 Entity를 보관함
        // Entity 조회 -> 그럼 Entity는 영속성 컨텍스트에 보관 -> 보관된 Entity의 객체 값이 변경되면 -> 트랜잭션이 커밋되는 시접에서 업데이트 쿼리 실행
        // 이렇게 자동으로 쿼리가 실행되는 개념을 더티체킹이라고 한다
        return id;
    }

    /*게시글 삭제*/
    @Transactional
    public Long delete(final Long id){
        Board board = boardRepository.findById(id)
                .orElseThrow(()-> new ApiException(ExceptionEnum.NOT_FOUND));

        board.deletedBoard();
        return id;
    }

    /*답글 등록 ver1*/
/*    @Transactional
    public void saveComment(Long boardId, Comment comment){
        Board board = boardRepository.findById(boardId)
                .orElseThrow(()->new CustomException(ErrorCode.NOT_FOUND));

        comment.saveComment(board);
        commentRepository.save(comment);
    }*/

    /*답글 등록 */
    @Transactional
    public Long saveComment(Long boardId, CommentDto.Request dto){
        Board board = boardRepository.findById(boardId)
                .orElseThrow(()->new ApiException(ExceptionEnum.NOT_FOUND));

        Comment comment = dto.toEntity(); // 받은 dto -> Entity로 변경
        comment.save(board);
        commentRepository.save(comment);
            // todo 방어코드 넣기 valid, id 있나 없나
        return dto.getId();
    }

    /*답글 수정*/
    @Transactional
    public Long updateComment(Long boardId, Long commentId, CommentDto.Request dto){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new ApiException(ExceptionEnum.NOT_FOUND));

        // todo 방어코드 넣기 valid, id 있나 없나
        comment.update(dto.getComment());

        return dto.getId();
    }

}

