package com.ssssong.choonsik.member.service;

import com.ssssong.choonsik.member.dao.MemberMapper;
import com.ssssong.choonsik.member.dto.MemberDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    private final MemberMapper memberMapper;

    @Autowired
    public MemberServiceTest(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Test
    void 멤버서비스_회원조회() {

        // given
        String memberId = "admin";

        // when
        MemberDTO memberDTO = memberMapper.selectByMemberId(memberId);

        // then
        assertNotNull(memberDTO);
    }

    @Test
    @Transactional
    @Rollback(false)
    void 멤버서비스_회원정보수정() {

        // given
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberId("user07");
        memberDTO.setMemberPassword("user07");
        memberDTO.setMemberName("수정명");
        memberDTO.setMemberEmail("mod@naver.com");

        // when
        int result = memberMapper.updateMember(memberDTO);

        // then
        assertEquals(1, result);
    }
}