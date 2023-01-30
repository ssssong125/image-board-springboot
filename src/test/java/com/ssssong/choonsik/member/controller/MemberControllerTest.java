package com.ssssong.choonsik.member.controller;

import com.ssssong.choonsik.member.dao.MemberMapper;
import com.ssssong.choonsik.member.dto.MemberDTO;
import com.ssssong.choonsik.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberControllerTest {

    private final MemberService memberService;

    private final PasswordEncoder passwordEncoder;

    private final MemberMapper memberMapper;

    @Autowired
    public MemberControllerTest(MemberService memberService, PasswordEncoder passwordEncoder, MemberMapper memberMapper) {

        this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
        this.memberMapper = memberMapper;
    }

    @Test
    void 멤버컨트롤러_멤버조회() {

        // given
        String memberId = "admin";

        // when
        MemberDTO memberDTO = memberService.selectMyInfo(memberId);

        // then
        assertNotNull(memberDTO);
    }

    @Test
    @Transactional
    @Rollback(false)
    void 멤버컨트롤러_멤버수정() {

        // given
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberId("user07");
        memberDTO.setMemberPassword("user07");
        memberDTO.setMemberName("2번이나 수정된");
        memberDTO.setMemberEmail("mod@naver.com");

        // when
        boolean result = memberService.updateMember(memberDTO);

        // then
        assertEquals(true, result);
    }
}