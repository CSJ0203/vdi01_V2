package com.example.vdi01.service;

import com.example.vdi01.domain.questionanswer.Board;
import com.example.vdi01.domain.questionanswer.BoardRepository;
import com.example.vdi01.dto.BoardDto;
import com.example.vdi01.exception.CustomException;
import com.example.vdi01.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequestMapping("/boards")
@RequiredArgsConstructor // final로 선언된 모든 멤버에 대한 생성자 만들어준댜
public class BoardService {

    private final BoardRepository boardRepository;

    /*게시글 생성*/
    @Transactional
    public Long save(final BoardDto.RequestDto dto){

        Board entity = boardRepository.save(dto.toEntity());
        return entity.getId();
    }

    /*게시글 리스트*/
    @Transactional
    public List<BoardDto.ResponseDto> findAll(){
        // 화면에서 받아올땐 Ajax대신 fetch
        // Ajax보다 API를 간단하게 호출한다는데 진짜일까 ㅇㅅㅇ
        List<Board> list = boardRepository.findAll();

        // java 8부터 생긴 Stream
        return list.stream() //boardRepository 뒤져서 나온 결과 list의 Stream을
                .map(BoardDto.ResponseDto::new) // map을 통해 BoardDto.ResponseDto로 변환해서, 참고로 map filter sorted 있음 노션 정리 ㄱ
                .collect(Collectors.toList()); // List로 반환
    }

    /*게시글 상세*/
    @Transactional
    public BoardDto.ResponseDto findById(Long id){

        Board entity = boardRepository.findById(id)
                .orElseThrow(()-> new CustomException(ErrorCode.NOT_FOUND)); // 게시글 없음

        return new BoardDto.ResponseDto(entity);

    }

    /*게시글 수정*/
    @Transactional
    public Long update(Long id, BoardDto.RequestDto dto){
        Board entity = boardRepository.findById(id)
                .orElseThrow(()-> new CustomException(ErrorCode.NOT_FOUND));

        entity.updateBoard(dto.getTitle(), dto.getContent(), dto.getDeleted());
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
        Board entity = boardRepository.findById(id)
                .orElseThrow(()-> new CustomException(ErrorCode.NOT_FOUND));

        entity.deletedBoard();
        return id;
    }

}

