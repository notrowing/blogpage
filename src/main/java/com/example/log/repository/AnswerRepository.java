package com.example.log.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.log.model.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Long> {

    List<Answer> findByMemberIdAndSeq(String memberId, long seq);
    List<Answer> findBySeq(long seq);
   
} 