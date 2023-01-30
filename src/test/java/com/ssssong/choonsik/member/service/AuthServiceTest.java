package com.ssssong.choonsik.member.service;

import com.ssssong.choonsik.jwt.TokenProvider;
import com.ssssong.choonsik.member.dao.MemberMapper;
import com.ssssong.choonsik.member.dto.MemberDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class AuthServiceTest {

    private final MemberMapper memberMapper;

    private final PasswordEncoder passwordEncoder;

    private final TokenProvider tokenProvider;

    @Autowired
    public AuthServiceTest(MemberMapper memberMapper, PasswordEncoder passwordEncoder, TokenProvider tokenProvider) {

        this.memberMapper = memberMapper;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @Test
    @Transactional
    @Rollback(false)
    void 오쓰서비스_회원가입() {

        // given
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberId("test02");
        memberDTO.setMemberPassword(passwordEncoder.encode("test02"));
        memberDTO.setMemberName("테스트");
        memberDTO.setMemberEmail("ychag@example.com");

        // when
        memberMapper.insertMember(memberDTO);

        // then
        assertNotNull(memberMapper.selectByMemberId(memberDTO.getMemberId()));
    }

    @Test
    void 오쓰서비스_로그인() {

        // given
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberId("admin");
        memberDTO.setMemberPassword("1234");

        // when


        // then
        assertEquals(memberDTO.getMemberId(), memberMapper.selectByMemberId(memberDTO.getMemberId()).getMemberId());
        assertEquals(true, passwordEncoder.matches(memberDTO.getMemberPassword(), memberMapper.selectByMemberId(memberDTO.getMemberId()).getMemberPassword()));
    }

    @Test
    @Transactional
    @Rollback(false)
    void 오쓰서비스_회원탈퇴() {

        // given
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberId("test02");
        memberDTO.setMemberPassword("test02");

        // when
        boolean password = passwordEncoder.matches(memberDTO.getMemberPassword(), memberMapper.selectByMemberId(memberDTO.getMemberId()).getMemberPassword());
        int result = memberMapper.memberWithdrawal(memberMapper.selectByMemberId(memberDTO.getMemberId()).getMemberCode());

        // then
        assertEquals(true, password);
        assertEquals(1, result);
    }
}