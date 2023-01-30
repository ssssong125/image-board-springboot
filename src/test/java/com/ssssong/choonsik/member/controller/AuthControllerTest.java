package com.ssssong.choonsik.member.controller;

import com.ssssong.choonsik.member.dto.MemberDTO;
import com.ssssong.choonsik.member.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class AuthControllerTest {

    @Autowired
    AuthService authService;


    @Autowired
    public AuthControllerTest(AuthService authService) {

        this.authService = authService;
    }

    @Test
    @Transactional
    @Rollback(false) // 값 들어가는지 확인하기위해 db상에 저장 = rollback 시킴
    void 오쓰컨트롤러_회원가입() {

        // given
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberId("test03");
        memberDTO.setMemberPassword("test03");
        memberDTO.setMemberName("test03");
        memberDTO.setMemberEmail("lyhxr@example.com");

        // when
        MemberDTO result = authService.signup(memberDTO);

        // then
        assertNotNull(result);
    }

    @Test
    void 오쓰컨트롤러_로그인() {

        // given
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberId("admin");
        memberDTO.setMemberPassword("1234");

        // when

        // then
        assertNotNull(authService.login(memberDTO));
    }

    @Test
    void memberWithdrawal() {

        // given
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberId("test03");
        memberDTO.setMemberPassword("test03");

        // when
        boolean withdraw = authService.memberWithdrawal(memberDTO);

        // then
        assertEquals(true, withdraw);
    }
}