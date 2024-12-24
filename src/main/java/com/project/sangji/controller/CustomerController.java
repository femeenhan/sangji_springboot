package com.project.sangji.controller;

import com.project.sangji.model.NoticeDTO;
import com.project.sangji.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customers")
class CustomerController {
    private final NoticeService ns;
    List<NoticeDTO> dto;

    @GetMapping("/cus_page1")
    public void customer1(Model model) {
        dto = ns.selectAll();
        System.out.println(dto.toString());
        model.addAttribute("list", dto);
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
    @GetMapping("/page_view/{no}")
    public String pageView(@PathVariable("no") int no, Model model) {
        model.addAttribute("data", ns.selectOne(no));
        return "customers/page_view";
    }
    @GetMapping("/write")
    public void write() {
    }

}
