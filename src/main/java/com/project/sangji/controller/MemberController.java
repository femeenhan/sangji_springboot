package com.project.sangji.controller;

import com.project.sangji.mapper.MemberMapper;
import com.project.sangji.model.MemberDTO;
import com.project.sangji.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;
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

    // 회원가입 결과 페이지 이동
    @GetMapping("/join_result")
    public void joinResult() {
    }

    // 로그인 페이지 이동
    @GetMapping("/login_main")
    public void loginMain() {
    }

    // 로그인 상태에서 회원정보 페이지 이동
    @GetMapping("/user_info")
    public void userInfo(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        MemberDTO memberDTO = ms.loginPwCheck(username);
        model.addAttribute("user", memberDTO);
    }

    // 로그인 하기 & 세션에 로그인 정보 담기
//    @PostMapping("/loginOk")
//    public String loginMain(@RequestParam("id") String id,
//                            @RequestParam("pw") String pw,
//                            @RequestParam(value = "saveId", required = false) String saveId,
//                            HttpServletResponse response,
//                            HttpSession session,
//                            RedirectAttributes rttr) {
//        MemberDTO user = ms.loginPwCheck(id);
//
//        // 아이디가 틀렸거나 입력하지 않았을 때
//        if (user == null) {
//            rttr.addFlashAttribute("message", "가입된 아이디가 아닙니다. 먼저 회원가입을 하세요.");
//            return "redirect:/member/login_main";
//        }
//
//        // pw 미입력 시
//        if (pw.isEmpty()) {
//            rttr.addFlashAttribute("message", "비밀번호를 입력하세요.");
//            return "redirect:/member/login_main";
//        }
//
//        // pw가 아이디와 일치하지 않을 때
//        // 패스워드 보안기능 적용 ex: if (!passwordEncoder.matches(pw, user.getPw()))
//        if (!user.getPw().equals(pw)) {
//            rttr.addFlashAttribute("message", "비밀번호가 틀렸습니다. 다시 입력해 주세요.");
//            return "redirect:/member/login_main";
//        }
//
//        // 아이디 저장 (쿠키) : 서버에서 쿠키 생성해서 쿠키를 관리하는 브라우저로 보내줘야 함
//
//        session.setAttribute("user", user);
//        session.setMaxInactiveInterval(1800);
//        return "redirect:/";
//    }

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
