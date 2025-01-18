package com.project.sangji.controller.rest;

import com.project.sangji.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class FetchController {
    private final MemberService ms;

    // join 아이디 체크
    @GetMapping("/idCheck/{id}")
    public ResponseEntity<Map<String, String>> idCheck(@PathVariable String id) {
        int idCheck = ms.idCheck(id);
        Map<String, String> response = new HashMap<>();
        // 사용 중인 아이디
        if (idCheck == 1) {
            response.put("message", "사용 중인 아이디입니다. 다른 아이디를 입력하세요.");
            response.put("status", "1");
            // 사용 가능한 아이디
        } else {
            response.put("message", "사용 가능한 아이디입니다.");
            response.put("status", "0");
        }
        return ResponseEntity.ok(response);
    }
}
