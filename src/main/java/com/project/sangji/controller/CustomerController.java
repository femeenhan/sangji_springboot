package com.project.sangji.controller;

import com.project.sangji.common.FileStorage;
import com.project.sangji.common.Pagination;
import com.project.sangji.model.BoardDTO;
import com.project.sangji.model.FileDTO;
import com.project.sangji.service.ListService;
import com.project.sangji.service.NoticeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customers")
class CustomerController {
    private final ListService ls;
    private final NoticeService ns;
    private final FileStorage fileStorage;
    List<BoardDTO> dto;
    Pagination pg = new Pagination();

    @GetMapping("/cus_page1")
    public void customer1(@RequestParam(defaultValue = "1") int pageNum,
                          HttpServletRequest request,
                          Model model) {
        pg.setPageNum(pageNum);
        pg.setTableName("notice");
//        System.out.println("table data = " + ns.selectAll(pg));
        int totalCount = ls.totalCount(pg);
        pg.setTotalRecord(totalCount);

        model.addAttribute("list", ls.selectAll(pg));
        model.addAttribute("paging", pg.paging(request));
    }

    @GetMapping("/cus_page2")
    public void customer2(@RequestParam(defaultValue = "1") int pageNum,
                          HttpServletRequest request,
                          Model model) {
        pg.setPageNum(pageNum);
        pg.setTableName("press");
        int totalCount = ls.totalCount(pg);
        pg.setTotalRecord(totalCount);
//        System.out.println("totalCount = " + totalCount);

        model.addAttribute("list", ls.selectAll(pg));
        model.addAttribute("paging", pg.paging(request));

    }

    @GetMapping("/cus_page3")
    public void customer3(@RequestParam(defaultValue = "1") int pageNum,
                          HttpServletRequest request,
                          Model model) {
        pg.setPageNum(pageNum);
        pg.setTableName("data");
        int totalCount = ls.totalCount(pg);
        pg.setTotalRecord(totalCount);

        model.addAttribute("list", ls.selectAll(pg));
        model.addAttribute("paging", pg.paging(request));

    }

    @GetMapping("/cus_page4")
    public void customer4(Model model) {

    }

    @GetMapping("/page1_view/{no}")
    public String page1View(@PathVariable("no") int no,
                            Model model) {
        model.addAttribute("dto", ns.selectOne(no));
        System.out.println(ns.selectOne(no));
        return "customers/page1_view";
    }

    @GetMapping("/page2_view/{no}")
    public String page2View(@PathVariable("no") int no,
                            Model model) {

        model.addAttribute("dto", ns.selectOne(no));
        return "customers/page2_view";
    }

    @GetMapping("/page3_view/{no}")
    public String page3View(@PathVariable("no") int no,
                            Model model) {

        model.addAttribute("dto", ns.selectOne(no));
        return "customers/page3_view";
    }

    @GetMapping("/write_notice")
    public String writeNotice(@ModelAttribute BoardDTO dto,
                              @RequestParam("file") MultipartFile[] files) {
        List<FileDTO> list = fileStorage.fileUpload(files);
        System.out.println(list.size());
        if (!list.isEmpty()) {
            dto.setOfile(list.getFirst().getOFile());
            dto.setNfile(list.getFirst().getNFile());
        }

        ns.insert(dto);
        return "redirect:/customers/cus_page1";
    }

    @GetMapping("/write_press")
    public void writePress() {
    }

    @GetMapping("/download/{no}")
    public ResponseEntity<InputStreamResource> download(@PathVariable("no") int no) {
        BoardDTO dto = ns.selectOne(no);
        if (dto != null) {
            return fileStorage.downloadFile(dto.getOfile(), dto.getNfile());
        }
        return ResponseEntity.notFound().build();
    }


}