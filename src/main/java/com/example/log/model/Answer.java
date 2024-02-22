package com.example.log.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity

public class Answer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long answerSeq;
    private long seq;
    private String answer;
    private String memberId;
}
