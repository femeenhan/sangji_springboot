package com.project.sangji.mapper;

import com.project.sangji.model.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PressMapper {

    BoardDTO selectOne(int no);

    void insert(BoardDTO notice);

    void update(BoardDTO notice);

    void delete(int no);
}