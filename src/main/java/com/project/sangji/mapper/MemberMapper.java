package com.project.sangji.mapper;

import com.project.sangji.model.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    // 회원가입
    void join(MemberDTO dto);

    // 아이디 중복체크
    int idCheck(String id);

    MemberDTO loginPwCheck(String id);

    MemberDTO login(String id, String pw);

    void updateMember(MemberDTO dto);
}

