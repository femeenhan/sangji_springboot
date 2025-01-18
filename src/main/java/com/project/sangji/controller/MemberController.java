package com.project.sangji.controller;

import com.project.sangji.mapper.MemberMapper;
import com.project.sangji.model.MemberDTO;
import com.project.sangji.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService ms;
    MemberDTO dto = new MemberDTO();
    // @RequiredArgsConstructor 대신 사용하려면,
//    private MemberService ms;
//    @Autowired
//    public MemberController(MemberService ms){
//      this.ms = ms;
//    }

    // 회원가입 페이지 이동
    @GetMapping("/join")
    public void join() {
    }

    // 회원가입 확인 및 결과 알림 페이지
    @GetMapping("/join_result")
    public void joinResult(String name, Model model) {
        model.addAttribute("name", name);
    }

    // 로그인 하기 & 세션에 로그인 정보 담기
    @GetMapping("/login_main")
    public void loginMain(String id, String pw, HttpSession session,
                          RedirectAttributes redirectAttributes) {

    }

    // 로그인 정보 찾기
    @GetMapping("/login_find_info")
    public void loginFindInfo() {
    }


}
