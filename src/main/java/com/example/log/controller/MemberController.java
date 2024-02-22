package com.example.log.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.log.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
// import org.springframework.web.bind.annotation.RequestParam;
import com.example.log.model.Member;

@Controller
public class MemberController {
     // 로그인 페이지
     @GetMapping("board/loginpage")
     public String loginBtn(){
         return "html/login";
     }
 
     @Autowired
     MemberRepository memberRepository;
     
     //로그인
     @PostMapping("/board/login")
     public String loginPost(@RequestParam("memberId") String memberId,
                             @RequestParam("memberPw") String memberPw,
                             HttpSession session){
         Member member;
         member = memberRepository.findByMemberIdAndMemberPw(memberId, memberPw);
         int count = memberRepository.findByMemberPwAndMemberId(memberPw,memberId).size();
         if(count<1){
            return "/html/loginfail";
        }
 
        session.setAttribute("member", member);
        return  "redirect:/";
     }
 
     //로그아웃 
     @GetMapping("/board/logout")
     public String logout(HttpSession session){
         session.invalidate();
         return "redirect:/";
     }
 
     // 회원 가입 페이지
     @GetMapping("/board/join")
     public String joinBtn(HttpSession session){
         Member member = new Member();
         member.setMemberId("admin");
         session.setAttribute("member", member);
         return "/html/join";
     }
 
     //회원가입
     @PostMapping("/board/join")
     public String memberPost(   @RequestParam("memberId") String memberId,
                                 @RequestParam("memberPw") String memberPw,
                                 @RequestParam("memberName") String memberName,
                                 @RequestParam("memberEmail") String memberEmail,
                                 HttpSession session){
         int count=memberRepository.findByMemberId(memberId).size();
          if(count > 0 ){
             return "/html/loginfail";
         }
         Member member = new Member();
         member.setMemberId(memberId);
         member.setMemberName(memberName);
         member.setMemberPw(memberPw);
         member.setMemberEmail(memberEmail);
         memberRepository.save(member);
 
         session.setAttribute("member", member);
        
         return "/html/login";
    }

}
