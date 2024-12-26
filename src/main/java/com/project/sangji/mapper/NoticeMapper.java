package com.project.sangji.mapper;

import com.mysql.cj.protocol.x.Notice;
import com.project.sangji.model.MemberDTO;
import com.project.sangji.model.NoticeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    List<NoticeDTO> selectAll();
    NoticeDTO selectOne(int no);
}