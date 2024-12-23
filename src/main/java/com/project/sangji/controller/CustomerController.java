package com.project.sangji.controller;

import com.project.sangji.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customers")
class CustomerController {
    private final NoticeService ns;

    @GetMapping("/cus_page1")
    public void customer1(Model model) {
        ns.selectAll();
    }
    @GetMapping("/cus_page2")
    public void customer2(Model model) {

    }
    @GetMapping("/cus_page3")
    public void customer3(Model model) {

    }
    @GetMapping("/cus_page4")
    public void customer4(Model model) {

    }
}
