package com.project.sangji.controller;

import com.project.sangji.mapper.MemberMapper;
import com.project.sangji.model.MemberDTO;
import com.project.sangji.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService ms;

    @GetMapping("/login_main")
    public void loginMain() {
    }

    @GetMapping("/login_find_info")
    public void loginFindInfo() {
    }

    @GetMapping("/join")
    public void join() {
    }
}
