package com.ssssong.choonsik.member.controller;

import com.ssssong.choonsik.jwt.TokenProvider;
import com.ssssong.choonsik.member.dao.MemberMapper;
import com.ssssong.choonsik.member.dto.MemberDTO;
import com.ssssong.choonsik.member.service.AuthService;
import com.ssssong.choonsik.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class AuthControllerTest {

    @Autowired
    AuthService authService;

    @Autowired
    MemberService memberService;

    private final MemberMapper memberMapper;

    private final PasswordEncoder passwordEncoder;

    private final TokenProvider tokenProvider;

    public AuthControllerTest(MemberMapper memberMapper, PasswordEncoder passwordEncoder, TokenProvider tokenProvider) {
        this.memberMapper = memberMapper;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @Test
    @Transactional
    @Rollback(false) // 값 들어가는지 확인하기위해 db상에 저장 = rollback 시킴
    void signup() {

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberId("test01");
        memberDTO.setMemberPassword("test01");

        authService.signup(memberDTO);

        System.out.println(memberMapper.selectByMemberId(memberDTO.getMemberId()));
    }

    @Test
    void login() {

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberId("admin");
        memberDTO.setMemberPassword("1234");

        authService.login(memberDTO);
    }

    @Test
    void memberWithdrawal() {



    }
}