package com.project.sangji.controller;

import com.project.sangji.common.FileStorage;
import com.project.sangji.common.Pagination;
import com.project.sangji.model.BoardDTO;
import com.project.sangji.model.FileDTO;
import com.project.sangji.service.DataService;
import com.project.sangji.service.ListService;
import com.project.sangji.service.NoticeService;
import com.project.sangji.service.PressService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customers")
class CustomerController {
    private final ListService ls;
    private final NoticeService ns;
    private final PressService ps;
    private final DataService ds;
    private final FileStorage fileStorage;
    List<BoardDTO> dto;
    Pagination pg = new Pagination();

    @GetMapping("/cus_page1")
    public void customer1(@RequestParam(defaultValue = "1") int pageNum,
                          @RequestParam(defaultValue = "title") String searchType,
                          @RequestParam(defaultValue = "") String keyword,
                          HttpServletRequest request,
                          Model model) {
        pg.setPageNum(pageNum);
        pg.setTableName("notice");

        searchFn(keyword, searchType, pg);

//        System.out.println("table data = " + ns.selectAll(pg));
        int totalCount = ls.totalCount(pg);
        pg.setTotalRecord(totalCount);

        model.addAttribute("list", ls.selectAll(pg));
        model.addAttribute("paging", pg.paging(request));
    }

    @GetMapping("/cus_page2")
    public void customer2(@RequestParam(defaultValue = "1") int pageNum,
                          @RequestParam(defaultValue = "title") String searchType,
                          @RequestParam(defaultValue = "") String keyword,
                          HttpServletRequest request,
                          Model model) {
        pg.setPageNum(pageNum);
        pg.setTableName("press");

        searchFn(keyword, searchType, pg);

        int totalCount = ls.totalCount(pg);
        pg.setTotalRecord(totalCount);
//        System.out.println("totalCount = " + totalCount);

        model.addAttribute("list", ls.selectAll(pg));
        model.addAttribute("paging", pg.paging(request));

    }

    @GetMapping("/cus_page3")
    public void customer3(@RequestParam(defaultValue = "1") int pageNum,
                          @RequestParam(defaultValue = "title") String searchType,
                          @RequestParam(defaultValue = "") String keyword,
                          HttpServletRequest request,
                          Model model) {
        pg.setPageNum(pageNum);
        pg.setTableName("data");

        searchFn(keyword, searchType, pg);

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

        model.addAttribute("dto", ps.selectOne(no));
        return "customers/page2_view";
    }

    @GetMapping("/page3_view/{no}")
    public String page3View(@PathVariable("no") int no,
                            Model model) {

        model.addAttribute("dto", ds.selectOne(no));
        return "customers/page3_view";
    }

    @GetMapping("/write_notice")
    public void writeNotice() {

    }

    @PostMapping("/write_notice")
    public String writeNotice(@ModelAttribute BoardDTO dto,
                              @RequestParam("file") MultipartFile[] files) {
        List<FileDTO> list = fileStorage.fileUpload(files);
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

    @PostMapping("/write_press")
    public String writePress(@ModelAttribute BoardDTO dto,
                             @RequestParam("file") MultipartFile[] files) {
        List<FileDTO> list = fileStorage.fileUpload(files);
        if (!list.isEmpty()) {
            dto.setOfile(list.getFirst().getOFile());
            dto.setNfile(list.getFirst().getNFile());
        }

        ps.insert(dto);
        return "redirect:/customers/cus_page2";
    }

    @GetMapping("/write_data")
    public void writeData() {
    }

    @PostMapping("/write_data")
    public String writeData(@ModelAttribute BoardDTO dto,
                            @RequestParam("file") MultipartFile[] files) {
        List<FileDTO> list = fileStorage.fileUpload(files);
        if (!list.isEmpty()) {
            dto.setOfile(list.getFirst().getOFile());
            dto.setNfile(list.getFirst().getNFile());
        }

        ds.insert(dto);
        return "redirect:/customers/cus_page3";
    }

    @GetMapping("/download/{no}")
    public ResponseEntity<InputStreamResource> download(@PathVariable("no") int no) {
        BoardDTO dto = ns.selectOne(no);
        if (dto != null) {
            return fileStorage.downloadFile(dto.getOfile(), dto.getNfile());
        }
        return ResponseEntity.notFound().build();
    }

    public void searchFn(String keyword, String searchType, Pagination pg) {
        if (keyword != null && !keyword.isEmpty()) {
            Map<String, String> searchMap = new HashMap<>();
            searchMap.put("searchType", searchType);
            searchMap.put("keyword", keyword);
            pg.setSearchMap(searchMap);
        } else {
            pg.setSearchMap(null);
        }
    }
}