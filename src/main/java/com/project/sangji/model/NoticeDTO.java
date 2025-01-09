package com.project.sangji.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeDTO {
    private int no;
    private String writer;
    private String title;
    private String content;
    private String regdate;
    private int visitcount;
    private String ofile;
    private String nfile;
    private String tableName = "notice";
}
