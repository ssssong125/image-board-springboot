package com.ssssong.choonsik.member.controller;

import com.ssssong.choonsik.common.ResponseDTO;
import com.ssssong.choonsik.member.dao.MemberMapper;
import com.ssssong.choonsik.member.dto.MemberDTO;
import com.ssssong.choonsik.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"Member"})
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

    @ApiOperation(value = "해당하는 아이디를 가진 회원 정보 조회")
    @GetMapping("/members/{memberId}")
    public ResponseEntity<ResponseDTO> selectMyMemberInfo(@PathVariable String memberId) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", memberService.selectMyInfo(memberId)));
    }

    @ApiOperation(value = "회원정보 수정")
    @Transactional
    @PutMapping("/member")
    public ResponseEntity<ResponseDTO> updateMember(@RequestBody MemberDTO member) {

        member.setMemberPassword(passwordEncoder.encode(member.getPassword()));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "회원정보 수정 성공", memberService.updateMember(member)));
    }
//
//    @Transactional
////    @PutMapping("/member/delete")
//    @DeleteMapping("/member")
//    public ResponseEntity<ResponseDTO> memberWithdrawal(@RequestBody MemberDTO memberDTO) {
//
////        MemberDTO member = memberMapper.selectByMemberId(memberDTO.getMemberId());
//
//        if (memberDTO != null) {
//            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "회원탈퇴 성공", memberService.memberWithdrawal(memberDTO)));
//        } else {
//            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "해당 회원을 찾지못했습니다.", null));
//        }
//    }

}
