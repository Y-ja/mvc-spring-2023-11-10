package com.spring.mvc.chap05.repository;


import com.spring.mvc.chap05.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberMapperTest {

    @Autowired
    MemberMapper memberMapper;


    @Test
    @DisplayName("회원가입에 성공해야 한다")
    void saveTest() {
        //given
        Member member = Member.builder()
                .account("lesserafim")
                .password("aaa1234")
                .name("라이옹")
                .email("abc@naver.com")
                .build();
        //when
        boolean flag = memberMapper.save(member);

        //then
        assertTrue(flag);
    }


    @Test
    @DisplayName("lesserafim계정을 조회하면 그 회원의 이름이 라이옹이어야 한다")
    void findMemberTest() {
        //given
        String acc = "lesserafim";
        //when
        Member foundMember = memberMapper.findMember(acc);
        //then
        System.out.println("foundMember = " + foundMember);
        assertEquals("라이옹", foundMember.getName());
    }


    @Test
    @DisplayName("계정이 newjeans일 경우 중복확인 결과값은 false이어야 한다.")
    void duplicateTest() {
        //given
        String acc = "newjeans";
        //when
        boolean flag = memberMapper.isDuplicate("account", acc);
        //then
        assertFalse(flag);
    }

    @Test
    @DisplayName("이메일이 abc@naver.com일 경우 중복확인 결과값은 true이어야 한다.")
    void duplicateEmailTest() {
        //given
        String email = "abc@naver.com";
        //when
        boolean flag = memberMapper.isDuplicate("email", email);
        //then
        assertTrue(flag);
    }

}