package com.project.sangji.mapper;

import com.project.sangji.model.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    // 회원가입
    void insertMember(MemberDTO memberDTO);

}

