package com.project.sangji.controller.rest;

import com.project.sangji.model.MemberDTO;
import com.project.sangji.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class FetchController {
    private final MemberService ms;
    private final PasswordEncoder passwordEncoder;


    // join 아이디 체크
    @GetMapping("/idCheck/{id}")
    public ResponseEntity<Map<String, String>> idCheck(@PathVariable String id) {
        int idCheck = ms.idCheck(id);
        System.out.println(idCheck);
        Map<String, String> response = new HashMap<>();
        if (idCheck == 1) {
            response.put("message", "사용 중인 아이디입니다. 다른 아이디를 입력하세요.");
            response.put("status", "1");
        } else {
            response.put("message", "사용 가능한 아이디입니다.\n사용하시겠습니까?");
            response.put("status", "0");
        }
        System.out.println(response);
        return ResponseEntity.ok(response);
    }

    // 회원가입 처리
    @PostMapping("/join_insert")
    public ResponseEntity<Map<String, String>> joinInsert(@RequestBody MemberDTO memberDTO) {
        Map<String, String> response = new HashMap<>();
        System.out.println("controller!!");
        try {
            // 비밀번호 암호화
            memberDTO.setPw(passwordEncoder.encode(memberDTO.getPw()));
            ms.join(memberDTO);
            System.out.println(memberDTO.toString());
            response.put("status", "OK");
            response.put("message", "회원가입이 완료되었습니다.");
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", "회원가입 처리 중 오류가 발생했습니다.");
        }
        System.out.println(response);
        return ResponseEntity.ok(response);
    }

    // 회원정보 수정 처리
    @PostMapping("/user_info_update")
    public ResponseEntity<Map<String, String>> updateMember(@RequestBody MemberDTO memberDTO) {
        Map<String, String> response = new HashMap<>();
        try {
            // 비밀번호가 변경된 경우에만 암호화
            if (memberDTO.getPw() != null && !memberDTO.getPw().isEmpty()) {
                memberDTO.setPw(passwordEncoder.encode(memberDTO.getPw()));
            }
            ms.updateMember(memberDTO);
            response.put("status", "OK");
            response.put("message", "회원정보가 수정되었습니다.");
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", "회원정보 수정 중 오류가 발생했습니다.");
        }
        return ResponseEntity.ok(response);
    }
}

