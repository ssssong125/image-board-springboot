package com.ssssong.choonsik.member.service;

import com.ssssong.choonsik.exception.DuplicatedUsernameException;
import com.ssssong.choonsik.exception.LoginFailedException;
import com.ssssong.choonsik.jwt.TokenProvider;
import com.ssssong.choonsik.member.dao.MemberMapper;
import com.ssssong.choonsik.member.dto.MemberDTO;
import com.ssssong.choonsik.member.dto.TokenDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class AuthService {

    private final MemberMapper memberMapper;

    private final PasswordEncoder passwordEncoder;

    private final TokenProvider tokenProvider;

//    public AuthService(MemberMapper memberMapper, PasswordEncoder passwordEncoder) {
//        this.memberMapper = memberMapper;
//        this.passwordEncoder = passwordEncoder;
//    }
    public AuthService(MemberMapper memberMapper, PasswordEncoder passwordEncoder, TokenProvider tokenProvider) {
        this.memberMapper = memberMapper;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @Transactional
    public MemberDTO signup(MemberDTO memberDTO) {

        log.info("[AuthService] Signup Start ===================================");
        log.info("[AuthService] MemberRequestDTO {}", memberDTO);

        // 아이디 중복 체크
        if(memberMapper.selectByMemberIdOnSignUp(memberDTO.getMemberId()) != null) {
            log.info("[AuthService] 아이디가 중복됩니다.");
            throw new DuplicatedUsernameException("아이디가 중복됩니다.");
        }

        // 이메일 중복 체크
        if(memberMapper.selectByEmail(memberDTO.getMemberEmail()) != null) {
            log.info("[AuthService] 이메일이 중복됩니다.");
            throw new DuplicatedUsernameException("이메일이 중복됩니다.");
        }

        log.info("[AuthService] Member Signup Start ==============================");
        memberDTO.setMemberPassword(passwordEncoder.encode(memberDTO.getMemberPassword()));

        log.info("[AuthService] Member {}", memberDTO);

        int result = memberMapper.insertMember(memberDTO);
        log.info("[AuthService] Member Insert Result {}", result > 0 ? "회원 가입 성공" : "회원 가입 실패");

        log.info("[AuthService] Signup End ==============================");

        return memberDTO;
    }

//    @Transactional
//    public MemberDto login(MemberDto memberDto) {
//        log.info("[AuthService] Login Start ===================================");
//        log.info("[AuthService] {}", memberDto);
//
//        // 1. 아이디 조회
//        MemberDto member = memberMapper.findByMemberId(memberDto.getMemberId())
//                .orElseThrow(() -> new LoginFailedException("잘못된 아이디 또는 비밀번호입니다"));
//
//        // 2. 비밀번호 매칭
//        if (!passwordEncoder.matches(memberDto.getMemberPassword(), member.getMemberPassword())) {
//            log.info("[AuthService] Password Match Fail!!!!!!!!!!!!");
//            throw new LoginFailedException("잘못된 아이디 또는 비밀번호입니다");
//        }
//
//        return member;
//    }

    @Transactional
    public TokenDTO login(MemberDTO memberDTO) {
        log.info("[AuthService] Login Start ===================================");
        log.info("[AuthService] {}", memberDTO);

        // 1. 아이디 조회
        MemberDTO member = memberMapper.findByMemberId(memberDTO.getMemberId())
                .orElseThrow(() -> new LoginFailedException("잘못된 아이디 또는 비밀번호입니다"));

        // 2. 비밀번호 매칭
        if (!passwordEncoder.matches(memberDTO.getMemberPassword(), member.getMemberPassword())) {
            log.info("[AuthService] Password Match Fail!!!!!!!!!!!!");
            throw new LoginFailedException("잘못된 아이디 또는 비밀번호입니다");
        }

        // 3. 토큰 발급
        TokenDTO tokenDTO = tokenProvider.generateTokenDto(member);
        log.info("[AuthService] tokenDto {}", tokenDTO);

        log.info("[AuthService] Login End ===================================");

        return tokenDTO;
    }

    public boolean memberWithdrawal(MemberDTO memberDTO) {

        MemberDTO member = memberMapper.selectByMemberId(memberDTO.getMemberId());

        if (member != null) {
            // 비밀번호 매칭
            if (!passwordEncoder.matches(memberDTO.getMemberPassword(), member.getMemberPassword())) {
                log.info("[AuthService] Password Match Fail!!!!!!!!!!!!");
                throw new LoginFailedException("잘못된 아이디 또는 비밀번호입니다");
            }

            return memberMapper.memberWithdrawal(member.getMemberCode()) > 0;
        } else {
            return false;
        }
    }
}
