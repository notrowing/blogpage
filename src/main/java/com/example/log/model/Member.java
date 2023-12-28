package com.example.log.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Member {
    @Id
    String memberId;
    String memberPw;
    String memberName;
    String memberEmail;
}
