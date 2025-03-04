package com.project.sangji.service;

import com.project.sangji.mapper.NoticeMapper;
import com.project.sangji.model.BoardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeMapper noticeMapper;

    public BoardDTO selectOne(int no) {
        return noticeMapper.selectOne(no);
    }

    public void insert(BoardDTO dto) {
        noticeMapper.insert(dto);
    }

    public void update(BoardDTO dto) {
        noticeMapper.update(dto);
    }

    public void delete(int no) {
        noticeMapper.delete(no);
    }
}
