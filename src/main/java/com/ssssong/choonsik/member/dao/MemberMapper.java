package com.ssssong.choonsik.member.dao;

import com.ssssong.choonsik.member.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {

    List<MemberDTO> selectAllMembers();

    Optional<MemberDTO> findByMemberId(String memberId);

    MemberDTO selectByEmail(String email);

    int insertMember(MemberDTO member);

    MemberDTO selectByMemberId(String memberId);

    int updateMember(MemberDTO member);

    int memberWithdrawal(long memberCode);

}
