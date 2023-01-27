package com.ssssong.choonsik.member.dao;

import com.ssssong.choonsik.member.dto.MemberDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
//@ContextConfiguration(classes = {Application})
class MemberMapperTest {

    @Autowired
    private MemberMapper memberMapper;

    @Test
    public void 맵퍼_의존성_주입_테스트() {

        assertNotNull(memberMapper);
    }

    @Test
    void 맵퍼_회원가입_테스트() {

        // given
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberName("테스트명");
        memberDTO.setMemberEmail("ychag@example.com");
        memberDTO.setMemberId("test01");
        memberDTO.setMemberPassword("test01");

        // when
        int result = memberMapper.insertMember(memberDTO);

        // then
        assertEquals(1, result);
    }

    @Test
    void 맵퍼_회원전체조회_테스트() {

        // given

        // when
        List<MemberDTO> memberDTOList = memberMapper.selectAllMembers();

        // then
        assertNotNull(memberDTOList);
    }

    @Test
    void 맵퍼_아이디로_회원찾기_테스트() {

        // given
        String memberId = "test01";

        // when
        MemberDTO memberDTO = memberMapper.selectByMemberId(memberId);

        // then
        assertNotNull(memberDTO);
    }

    @Test
    void 맵퍼_이메일로_회원찾기_테스트() {

        // given
        String memberEmail = "ychag@example.com";

        // when
        MemberDTO memberDTO = memberMapper.selectByEmail(memberEmail);

        // then
        assertNotNull(memberDTO);
    }

    @Test
    void 맵퍼_아이디로_회원찾기_옵셔널_테스트() {

        // given
        String memberId = "test01";

        // when
        MemberDTO memberDTO = memberMapper.selectByMemberId(memberId);

        // then
        assertNotNull(memberDTO);
    }

    @Test
    void 맵퍼_회원가입시_아이디로_회원찾기_테스트() {

        // given
        String memberId = "test01";

        // when
        MemberDTO memberDTO = memberMapper.selectByMemberId(memberId);

        // then
        assertNotNull(memberDTO);
    }

    @Test
    @Transactional
    @Rollback(false)
    void 맵퍼_회원정보수정_테스트() {

        // given
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberId("test01");
        memberDTO.setMemberPassword("modify01");
        memberDTO.setMemberName("수정테스트명");
        memberDTO.setMemberEmail("modify@example.com");

        // when
        int result = memberMapper.updateMember(memberDTO);

        // then
        assertEquals(1, result);
    }

    @Test
    void 맵퍼_회원탈퇴_테스트() {

        // given
        long memberCode = 16;

        // when
        int result = memberMapper.memberWithdrawal(memberCode);

        // then
        assertEquals(1, result);
    }
}