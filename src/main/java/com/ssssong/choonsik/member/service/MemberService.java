package com.ssssong.choonsik.member.service;

import com.ssssong.choonsik.member.dao.MemberMapper;
import com.ssssong.choonsik.member.dto.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Slf4j
@Service
public class MemberService {
    private final MemberMapper memberMapper;

//    private final PasswordEncoder passwordEncoder;

    public MemberService(MemberMapper memberMapper/*, PasswordEncoder passwordEncoder*/) {
        this.memberMapper = memberMapper;
//        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public MemberDTO selectMyInfo(@PathVariable String memberId) {
        log.info("[MemberService] getMyInfo Start ==============================");

        MemberDTO member = memberMapper.selectByMemberId(memberId);
        log.info("[MemberService] {}", member);
        log.info("[MemberService] getMyInfo End ==============================");

        return member;
    }

    public List<MemberDTO> selectAllMembers() {

        return memberMapper.selectAllMembers();
    }

    public boolean updateMember(MemberDTO member) {

        return memberMapper.updateMember(member) > 0;
    }
//
//    public boolean memberWithdrawal(MemberDTO memberDTO) {
//
//        // 비밀번호 매칭
//        if (!passwordEncoder.matches(memberDTO.getMemberPassword(), memberDTO.getMemberPassword())) {
//            log.info("[AuthService] Password Match Fail!!!!!!!!!!!!");
//            throw new LoginFailedException("잘못된 아이디 또는 비밀번호입니다");
//        }
//
//        MemberDTO member = memberMapper.selectByMemberId(memberDTO.getMemberId());
//
//        return memberMapper.memberWithdrawal(member.getMemberCode()) > 0;
//    }

}
