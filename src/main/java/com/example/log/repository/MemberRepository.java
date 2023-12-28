package com.example.log.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.log.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String>{
    Member findByMemberIdAndMemberPw(String memberId, String memberPw);
    List<Member> findByMemberPwAndMemberId(String memberPw, String memberId);
    List<Member> findByMemberId(String memberId);
}