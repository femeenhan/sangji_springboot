package com.project.sangji.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/procedure")
public class ProcedureController {
    @GetMapping("/proc_page1")
    public void proc_page1() {

    }
    @GetMapping("/proc_page2")
    public void proc_page2() {

    }
    @GetMapping("/proc_page3")
    public void proc_page3() {

    }
}
