package com.project.sangji.service;

import com.project.sangji.mapper.MemberMapper;
import com.project.sangji.mapper.NoticeMapper;
import com.project.sangji.model.MemberDTO;
import com.project.sangji.model.NoticeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;

    public void insertMember(MemberDTO memberDTO) {}

}
