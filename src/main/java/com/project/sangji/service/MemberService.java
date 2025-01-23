package com.project.sangji.service;

import com.project.sangji.mapper.MemberMapper;
import com.project.sangji.model.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;

    public int idCheck(String id) {
        return memberMapper.idCheck(id);
    }

    public void join(MemberDTO dto) {
        memberMapper.join(dto);
    }

    public MemberDTO loginPwCheck(String id) {
        return memberMapper.loginPwCheck(id);
    }

    public MemberDTO login(String id, String pw) {
        return memberMapper.login(id, pw);
    }

    public void updateMember(MemberDTO dto) {
        memberMapper.updateMember(dto);
    }

}
