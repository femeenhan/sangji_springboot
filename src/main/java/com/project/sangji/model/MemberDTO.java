package com.project.sangji.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private String id;
    private String pw;
    private String name;
    private String email;
    private String phone;
    private String birth;
    private String zipcode;
    private String addr1;
    private String addr2;
    private Date regdate;
    private Character isAdmin;
}
