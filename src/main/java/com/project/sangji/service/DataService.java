package com.project.sangji.service;

import com.project.sangji.mapper.DataMapper;
import com.project.sangji.mapper.NoticeMapper;
import com.project.sangji.model.BoardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DataService {
    private final DataMapper dataMapper;

    public BoardDTO selectOne(int no) {
        return dataMapper.selectOne(no);
    }

    public void insert(BoardDTO dto) {
        dataMapper.insert(dto);
    }

    public void update(BoardDTO dto) {
        dataMapper.update(dto);
    }

    public void delete(int no) {
        dataMapper.delete(no);
    }
}
