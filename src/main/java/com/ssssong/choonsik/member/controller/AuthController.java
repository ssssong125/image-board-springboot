package com.ssssong.choonsik.member.controller;

import com.ssssong.choonsik.common.ResponseDTO;
import com.ssssong.choonsik.member.dto.MemberDTO;
import com.ssssong.choonsik.member.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"Auth"})
@RestController
@RequestMapping("/api/v2/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ApiOperation(value = "사이트에 회원가입")
    @PostMapping("/regist")
    public ResponseEntity<ResponseDTO> signup(@RequestBody MemberDTO memberDTO) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "회원가입 성공", authService.signup(memberDTO)));
    }

    @ApiOperation(value = "사이트에 로그인")
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody MemberDTO memberDTO) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "로그인 성공", authService.login(memberDTO)));
    }

    @ApiOperation(value = "회원 탈퇴 - 탈퇴시 비밀번호 입력받아")
    @Transactional
//    @PutMapping("/member/delete")
    @DeleteMapping("/member")
    public ResponseEntity<ResponseDTO> memberWithdrawal(@RequestBody MemberDTO memberDTO) {

//        MemberDTO member = memberMapper.selectByMemberId(memberDTO.getMemberId());

        if (authService.memberWithdrawal(memberDTO)) {
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "회원탈퇴 성공", null));
        } else {
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "해당 회원을 찾지못했습니다.", null));
        }
    }
}
