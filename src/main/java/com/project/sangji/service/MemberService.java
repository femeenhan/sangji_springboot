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

    public MemberDTO login(String id, String pw) {
        MemberDTO dto = new MemberDTO();
        dto.setId(id);
        dto.setPw(pw);
        return memberMapper.login(dto);
    }

}
