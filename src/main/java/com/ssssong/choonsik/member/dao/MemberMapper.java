package com.ssssong.choonsik.member.dao;

import com.ssssong.choonsik.member.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {

    int insertMember(MemberDTO member);

    List<MemberDTO> selectAllMembers();

    Optional<MemberDTO> findByMemberId(String memberId);

    MemberDTO selectByEmail(String email);

    MemberDTO selectByMemberId(String memberId);

    MemberDTO selectByMemberIdOnSignUp(String memberId);

    int updateMember(MemberDTO member);

    int memberWithdrawal(long memberCode);

}
