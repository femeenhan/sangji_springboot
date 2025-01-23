package com.project.sangji.security;

import com.project.sangji.mapper.MemberMapper;
import com.project.sangji.model.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUsersDetailsService implements UserDetailsService {
    private final MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberDTO memberDTO = memberMapper.loginPwCheck(username);
        if (memberDTO == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.: " + username);
        }
        return new CustomUserDetails(memberDTO);
    }
}
