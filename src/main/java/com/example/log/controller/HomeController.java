package com.example.log.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.example.log.model.Board;

import com.example.log.model.Answer;

import com.example.log.repository.AnswerRepository;
import com.example.log.repository.BoardRepository;

import com.example.log.service.Boardservice;

import jakarta.servlet.http.HttpSession;



@Controller
public class HomeController {
    @Autowired
    BoardRepository boardRepository;

    //메인 페이지 board
    @GetMapping("/")
    public String boardList1(Model model){
        List<Board> data =  boardRepository.findAll();
        model.addAttribute("noteList", data);
        return "html/main";
    }

    // 글쓰기 버튼 
    @GetMapping("board/input")
    public String inputBtn(){
        return "html/insert";
    }

    // 글 작성 
    @PostMapping("board/insert")
    public String BoardNote(Board board,
                            @RequestParam("memberId") String memberId,
                            @RequestParam("memberName") String memberName){
        boardRepository.save(board);
        return "redirect:/";
    }
 
    //글 삭제 버튼
    @GetMapping("/board/delete")
    public String boardDelete(long seq){
        boardRepository.deleteById(seq);
        return "redirect:/";
    }

    @Autowired
    Boardservice boardservice;

    //글 수정 버튼
    @GetMapping("/board/update")
    public String updateBtn(long seq, Model model){
        model.addAttribute("board", boardservice.boardView(seq));
        return "/html/update";
    }

    //글 수정
    @PostMapping("/board/modify")
    public String boardUpdate(long seq, Board board){
        Board boardTemp = boardservice.boardView(seq);
        boardTemp.setSeq(board.getSeq());
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());
        boardTemp.setMemberName(board.getMemberName());
        boardTemp.setMemberId(board.getMemberId());
        boardRepository.save(boardTemp);
        return "redirect:/";
    }

    @Autowired
    AnswerRepository answerRepository;

    // 상세보기
    @GetMapping("/board/detail")
    public String detailBtn(long seq,Board board, Model model,
                             HttpSession httpsession){
        model.addAttribute("board", boardservice.boardView(seq));
        
        // 댓글
        List<Answer> data = answerRepository.findBySeq(seq);
        model.addAttribute("cmtList",data);
        return "/html/detail";
    }

    // 댓글 입력 
    @PostMapping("/board/cmtbox")
    public String cmtInput(String answer, long seq ,String memberId, 
                            Board board, HttpSession httpsession, Model model){
        Answer comment = new Answer();
        comment.setAnswer(answer);
        comment.setSeq(seq);
        comment.setMemberId(memberId);
        answerRepository.save(comment);

        List<Answer> data = answerRepository.findBySeq(seq);
        model.addAttribute("cmtList",data);

        model.addAttribute("board", boardservice.boardView(seq));
        return "html/detail";
    } 

    // 댓글 삭제
    @GetMapping("/board/cmtdelete")
    public String DeleteCmt(long answerSeq,long seq, Board board, Model model,
                             HttpSession httpsession){
        List<Answer> data = answerRepository.findBySeq(seq);
        model.addAttribute("cmtList",data);
    
        model.addAttribute("board", boardservice.boardView(seq));
        answerRepository.deleteById(answerSeq); // 댓글 삭제
        return "html/detail";
    }

}
