package com.example.vdi01.domain.questionanswer;

import com.example.vdi01.dto.BoardResponseDto;
import com.example.vdi01.dto.BoardSearchDto;
import com.example.vdi01.dto.QBoardResponseDto;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.el.parser.BooleanNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.vdi01.domain.questionanswer.QBoard.board;

public class BoardRepositoryImpl extends QuerydslRepositorySupport implements BoardRepositoryCustom {

    private JPAQueryFactory queryFactory;

    public BoardRepositoryImpl(){
        super(Board.class);
    }

//    QBoard board = new QBoard("b");

//    @Override
//    public void setEntityManager(EntityManager entityManager) {
//        super.setEntityManager(entityManager);
//        queryFactory = new JPAQueryFactory(entityManager);
//    }

    @Override
//    public List<BoardResponseDto> findAllByBoardSearch(BoardSearchDto boardSearchDto) { // 검색쿼리
    public List<BoardResponseDto> findAllByBoardSearch(BoardSearchDto boardSearchDto, Pageable pageable) { // 검색쿼리


        //이거 쓰려면 queryFactory가 왜 null인지 알아야함
//        return queryFactory
//                .selectFrom(board)
//                .where(
//                        likeTitle(boardSearchDto.getTitle()),
//                        likeWriter(boardSearchDto.getWriter())
//                )
//                .fetch();

//        JPQLQuery<Board> query = queryFactory
//                .selectFrom(board) // 여기서부터 익셉션으로 빠짐 내일와서 하기
//                .where(eqTitle(boardSearchDto.getTitle()), eqWriter(boardSearchDto.getWriter()));
        JPQLQuery<BoardResponseDto> query = getQuerydsl().createQuery();

        List<BoardResponseDto> test =  query
                .select(
                        new QBoardResponseDto(
                                board.title,
                                board.writer,
                                board.createDate
                                )
                )
                .from(board)
                .where(
                        likeTitle(boardSearchDto.getTitle()),
                        likeWriter(boardSearchDto.getWriter())
                )
                .fetch();

        return test;

//        List<BoardResponseDto> boards = this.getQuerydsl().applyPagination(pageable, query).fetch(); // fetch == 리스트 조회


//        return new PageImpl<Board>(boards, pageable, query.fetchCount());
//        return (List<Board>) new PageImpl<Board>(boards, pageable, query.fetchCount());

    }

    @Override
    public List<BoardResponseDto> findAllByBoardSearchLike(BoardSearchDto boardSearchDto, Pageable pageable) {
        JPQLQuery<BoardResponseDto> query = getQuerydsl().createQuery();

        List<BoardResponseDto> test =  query
                .select(
                        new QBoardResponseDto(
                                board.title,
                                board.writer,
                                board.createDate
                        )
                )
                .from(board)
                .where(
                        likeTitle(boardSearchDto.getTitle()),
                        likeWriter(boardSearchDto.getWriter())
                )
                .fetch();

        return test;
    }

    @Override
    public List<BoardResponseDto> findAllByBoardSearchEq(BoardSearchDto boardSearchDto, Pageable pageable) {
        JPQLQuery<BoardResponseDto> query = getQuerydsl().createQuery();
        // dto를 큐파일로 만들어서 사용, 만들고자 하는 생성자에 QueryProjection 달아주고 컴파일
        // Runtime이 아닌 컴파일 타임에서 잡을 수 있다는 장점

        /**
         * querydsl 3가지 방법
         * 1.Setter 사용
         * 2.Projection.fields 사용
         * 3.Projection.constructor 사용
         * 4.@QueryPorjection 사용
         */
        List<BoardResponseDto> test =  query
                .select(
                        new QBoardResponseDto(
                                board.title,
                                board.writer,
                                board.createDate
                        )
                )
                .from(board)
                .where(
                        eqTitle(boardSearchDto.getTitle()),
                        eqWriter(boardSearchDto.getWriter())
                )
                .fetch();

//        for (Sort.Order o :pageable.getSort()) {
//            PathBuilder pathBuilder = new PathBuilder(query.getType(), query.getMetadata());
//            query.orderBy(new OrderSpecifier(o.isAscending()) ? Order.ASC)
//        }




        return test;
    }

    private BooleanExpression likeTitle(String title){
        if(ObjectUtils.isEmpty(title)){
            return null;
        }

        return board.title.like("%"+title+"%");
    }

    private BooleanExpression likeWriter(String writer){
        if(ObjectUtils.isEmpty(writer)){
            return null;
        }

        return board.writer.like("%"+writer+"%");
    }

    private BooleanExpression eqTitle(String title){
        if(ObjectUtils.isEmpty(title)){
            return null;
        }

        return board.title.eq(title);
    }

    private BooleanExpression eqWriter(String writer){
        if(ObjectUtils.isEmpty(writer)){
            return null;
        }

        return board.writer.eq(writer);
    }

//    private OrderSpecifier<?> orderType(BoardSearchDto dto){
//
//    }

    //todo 화면상에 파라미터에 따라 like 비교와 equals에 따라 달라야함, 정렬 완료
    // todo writer, id, title 가변 정렬
}
