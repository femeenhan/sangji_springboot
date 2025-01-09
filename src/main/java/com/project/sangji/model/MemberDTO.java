package com.project.sangji.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private String id;
    private String pass;
    private String name;
    private String email;
    private String phone;
    private String birth;
    private String zipcode;
    private String address;
    private String addr_detail;
    private String isAdmin;
    private String tableName = "member";

}
