package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.request.SignUpRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MemberServiceTest {
   @Autowired
    MemberService memberService;
   @Test
   @DisplayName("회원정보를 전달하면 비밀번호가 암호화 되어 디비에 저장")
    void joinTest(){
       SignUpRequestDTO dto = SignUpRequestDTO.builder()
               .account("kitty")
               .password("kkk1234")
               .name("헬로키티")
               .email("kitty1234@naver.com")
               .build();
   }
}