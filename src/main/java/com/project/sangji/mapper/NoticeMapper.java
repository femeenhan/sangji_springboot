package com.project.sangji.mapper;

import com.project.sangji.common.Pagination;
import com.project.sangji.model.NoticeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface NoticeMapper {
    List<NoticeDTO> selectAll(Pagination pg);
    NoticeDTO selectOne(int no);
    Integer totalCount(Pagination pg);
    void insert(NoticeDTO notice);
    void update(NoticeDTO notice);
    void delete(int no);
}