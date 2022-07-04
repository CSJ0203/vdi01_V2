package com.example.vdi01.domain.questionanswer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> { // Entity클래스, PK타입
}
