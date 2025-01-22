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
    // @RequiredArgsConstructor
    // 대신 사용하려면,
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
        return "member/join_result";
    }

    // 로그인 페이지 이동
    @GetMapping("/login_main")
    public void loginMain(Model model) {
    }

    // 로그인 상태에서 회원정보 페이지 이동
    @GetMapping("/user_info")
    public void userInfo(Model model) {

    }

    // 로그인 하기 & 세션에 로그인 정보 담기
    @PostMapping("/loginOk")
    public String loginMain(@RequestParam("id") String id,
                            @RequestParam("pw") String pw, Model model,
                            HttpSession session,
                            RedirectAttributes rttr) {
        MemberDTO pwCheck = ms.loginPwCheck(id);
        System.out.println(pwCheck.toString());
        MemberDTO user = ms.login(id, pw);
        System.out.println(user.toString());
        if (pwCheck.getId() == null) {
            rttr.addFlashAttribute("message", "가입된 아이디가 아닙니다. 먼저 회원가입을 하세요.");
            return "redirect:/login_main";
        } else if (user.getId().equals(id) && !user.getPw().equals(pw)) {
            rttr.addFlashAttribute("message", "비밀번호가 틀렸습니다.");
            return "redirect:/login_main";
        } else if (user != null) {
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(1800);
            return "redirect:/";
        } else {
            rttr.addFlashAttribute("message", "아이디와 비밀번호가 일치하지 않습니다.");
            return "redirect:/login_main";
        }
    }

    // 로그인 정보 찾기
    @GetMapping("/login_find_info")
    public void loginFindInfo() {
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
