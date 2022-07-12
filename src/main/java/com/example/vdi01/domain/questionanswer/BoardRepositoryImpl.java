package com.example.vdi01.domain.questionanswer;

import com.example.vdi01.dto.BoardResponseDto;
import com.example.vdi01.dto.BoardSearchDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.el.parser.BooleanNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import java.util.List;

public class BoardRepositoryImpl extends QuerydslRepositorySupport implements BoardRepositoryCustom {

    private JPAQueryFactory queryFactory;

    public BoardRepositoryImpl(){
        super(Board.class);
    }

    QBoard board = new QBoard("b");

//    @Override
//    public void setEntityManager(EntityManager entityManager) {
//        super.setEntityManager(entityManager);
//        queryFactory = new JPAQueryFactory(entityManager);
//    }

    @Override
//    public List<BoardResponseDto> findAllByBoardSearch(BoardSearchDto boardSearchDto) { // 검색쿼리
    public List<Board> findAllByBoardSearch(BoardSearchDto boardSearchDto, Pageable pageable) { // 검색쿼리
/*        return queryFactory
                .selectFrom(board)
                .where(eqTitle(boardSearchDto.getTitle()), eqWriter(boardSearchDto.getWriter()))
                .fetch();*/

        JPQLQuery<Board> query = queryFactory
                .selectFrom(QBoard.board)
                .where(eqTitle(boardSearchDto.getTitle()), eqWriter(boardSearchDto.getWriter()));

        List<Board> boards = this.getQuerydsl().applyPagination(pageable, query).fetch();

        return (List<Board>) new PageImpl<Board>(boards, pageable, query.fetchCount());

    }

    private BooleanExpression eqTitle(String title){
        if(title.isEmpty() || title == null){
            return null;
        }

        return QBoard.board.title.eq(title);
    }

    private BooleanExpression eqWriter(String writer){
        if(writer.isEmpty() || writer == null){
            return null;
        }

        return QBoard.board.writer.eq(writer);
    }

}
