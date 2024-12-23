package com.project.sangji.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main_business")
public class BusinessController {
    @GetMapping("/biz_page1")
    public void biz_page1() {
    }
    @GetMapping("/biz_page2")
    public void biz_page2() {
    }
    @GetMapping("/biz_page3")
    public void biz_page3() {
    }
    @GetMapping("/biz_page4")
    public void biz_page4() {
    }
}
