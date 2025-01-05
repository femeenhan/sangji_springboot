package com.project.sangji.controller;

import com.project.sangji.common.Pagination;
import com.project.sangji.model.NoticeDTO;
import com.project.sangji.service.NoticeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customers")
class CustomerController {

    private final NoticeService ns;
    List<NoticeDTO> dto;
    Pagination pg = new Pagination();

    @GetMapping("/cus_page1")
    public void customer1(@RequestParam(defaultValue = "1") int pageNum,
                          HttpServletRequest request,
                          Model model) {
        pg.setPageNum(pageNum);
        pg.setTableName("notice");
        System.out.println("table data = " + ns.selectAll(pg));
        int totalCount = ns.totalCount(pg);
        pg.setTotalRecord(totalCount);

        model.addAttribute("list", ns.selectAll(pg));
        model.addAttribute("paging", pg.paging(request));
    }

    @GetMapping("/cus_page2")
    public void customer2(@RequestParam(defaultValue = "1") int pageNum,
                          HttpServletRequest request,
                          Model model) {
        pg.setPageNum(pageNum);
        pg.setTableName("press_article");
        int totalCount = ns.totalCount(pg);
        pg.setTotalRecord(totalCount);
//        System.out.println("totalCount = " + totalCount);

        model.addAttribute("list", ns.selectAll(pg));
        model.addAttribute("paging", pg.paging(request));

    }

    @GetMapping("/cus_page3")
    public void customer3(@RequestParam(defaultValue = "1") int pageNum,
                          HttpServletRequest request,
                          Model model) {
        pg.setPageNum(pageNum);
        pg.setTableName("data_board");
        int totalCount = ns.totalCount(pg);
        pg.setTotalRecord(totalCount);

        model.addAttribute("list", ns.selectAll(pg));
        model.addAttribute("paging", pg.paging(request));

    }

    @GetMapping("/cus_page4")
    public void customer4(Model model) {

    }

    @GetMapping("/page_view/{no}")
    public String pageView(@PathVariable("no") int no,
                           Model model,
                           HttpServletRequest request) {

        System.out.println("pathInfo = " + request.getPathInfo());
        model.addAttribute("link", "/customers");
        model.addAttribute("dto", ns.selectOne(no));
        return "customers/page_view";
    }

    @GetMapping("/write_notice")
    public void writeNotice() {
    }

    @GetMapping("/write_press")
    public void writePress() {
    }

}
