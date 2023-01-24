package com.ssssong.choonsik.member.controller;

import com.ssssong.choonsik.common.ResponseDTO;
import com.ssssong.choonsik.exception.LoginFailedException;
import com.ssssong.choonsik.member.dao.MemberMapper;
import com.ssssong.choonsik.member.dto.MemberDTO;
import com.ssssong.choonsik.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MemberController {
    private final MemberService memberService;

    private final PasswordEncoder passwordEncoder;

    private final MemberMapper memberMapper;

    public MemberController(MemberService memberService, PasswordEncoder passwordEncoder, MemberMapper memberMapper) {

        this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
        this.memberMapper = memberMapper;
    }

    @GetMapping("/members/{memberId}")
    public ResponseEntity<ResponseDTO> selectMyMemberInfo(@PathVariable String memberId) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", memberService.selectMyInfo(memberId)));
    }

    @Transactional
    @PutMapping("/member/update")
    public ResponseEntity<ResponseDTO> updateMember(@RequestBody MemberDTO member) {

        member.setMemberPassword(passwordEncoder.encode(member.getPassword()));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "회원정보 수정 성공", memberService.updateMember(member)));
    }

    @Transactional
//    @PutMapping("/member/delete")
    @DeleteMapping("/member/delete")
    public ResponseEntity<ResponseDTO> memberWithdrawal(@RequestBody MemberDTO memberDTO) {

        MemberDTO member = memberMapper.selectByMemberId(memberDTO.getMemberId());

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "회원탈퇴 성공", memberService.memberWithdrawal(member.getMemberCode())));
    }

}
