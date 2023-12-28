package com.example.log.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.log.model.Board;


@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    
}