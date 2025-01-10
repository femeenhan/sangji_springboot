package com.project.sangji.service;

import com.project.sangji.mapper.DataMapper;
import com.project.sangji.mapper.PressMapper;
import com.project.sangji.model.BoardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PressService {
    private final PressMapper pressMapper;

    public BoardDTO selectOne(int no) {
        return pressMapper.selectOne(no);
    }

    public void insert(BoardDTO dto) {
        pressMapper.insert(dto);
    }

    public void update(BoardDTO dto) {
        pressMapper.update(dto);
    }

    public void delete(int no) {
        pressMapper.delete(no);
    }
}
