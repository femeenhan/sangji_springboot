package com.project.sangji.service;

import com.project.sangji.mapper.MemberMapper;
import com.project.sangji.model.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;

    public void insertMember(MemberDTO memberDTO) {
    }

}
