package com.project.sangji.controller;

import com.project.sangji.mapper.MemberMapper;
import com.project.sangji.model.MemberDTO;
import com.project.sangji.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    // 회원가입 입력 페이지 이동
    @GetMapping("/join")
    public void join() {
    }

    // 회원가입 입력 결과
    @PostMapping("/join_insert")
    public String joinInsert(@ModelAttribute MemberDTO dto, Model model) {
        ms.join(dto);
        model.addAttribute("name", dto.getName());
        return "/member/join_result";
    }

    // 로그인 페이지 이동
    @GetMapping("/login_main")
    public void loginMain(Model model) {
    }

    // 로그인 하기 & 세션에 로그인 정보 담기
    @PostMapping("/loginOk")
    public void loginMain(@RequestParam("id") String id,
                          @RequestParam("pw") String pw, Model model) {
        int loginResult = ms.login(id, pw);
        if (loginResult == 1) {
            MemberDTO dto = new MemberDTO();
            System.out.println(dto.toString());
        }
    }

    // 로그인 정보 찾기
    @GetMapping("/login_find_info")
    public void loginFindInfo() {
    }


}
