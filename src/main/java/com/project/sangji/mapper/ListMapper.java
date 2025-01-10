package com.project.sangji.mapper;

import com.project.sangji.common.Pagination;
import com.project.sangji.model.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ListMapper {
    List<BoardDTO> selectAll(Pagination pg);

    Integer totalCount(Pagination pg);
}