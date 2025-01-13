package com.project.sangji.mapper;

import com.project.sangji.model.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeMapper {

    BoardDTO selectOne(int no);

    void insert(BoardDTO dto);

    void update(BoardDTO dto);

    void delete(int no);
}