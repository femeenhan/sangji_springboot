package com.project.sangji.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {
    @GetMapping("/login_main")
    public void loginMain() {
    }

    @GetMapping("/login_find_info")
    public void loginFindInfo() {
    }

    @GetMapping("/join")
    public void join() { }
}
