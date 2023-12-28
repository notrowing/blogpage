package com.example.log.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.log.model.Board;
import com.example.log.repository.BoardRepository;

@Service
public class Boardservice {
    @Autowired
    private BoardRepository boardRepository;
    public void write(Board board){
        boardRepository.save(board);
    }

    public void boardDelete(long seq){
        boardRepository.deleteById(seq);
    }

    public Board boardView(long seq){
        return boardRepository.findById(seq).get();
    }

    
}
