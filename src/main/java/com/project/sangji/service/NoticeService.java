package com.project.sangji.service;

import com.project.sangji.common.Pagination;
import com.project.sangji.mapper.NoticeMapper;
import com.project.sangji.model.NoticeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeMapper noticeMapper;

    public List<NoticeDTO> selectAll(Pagination pg) {
        return noticeMapper.selectAll(pg);
    }

    public NoticeDTO selectOne(int no) {
        return noticeMapper.selectOne(no);
    }

    public Integer totalCount(Pagination pg) {
        Integer count = noticeMapper.totalCount(pg);
        return count != null ? count : 0;
    }
    
    public void insert(NoticeDTO dto) {
        noticeMapper.insert(dto);
    }

    public void update(NoticeDTO dto) {
        noticeMapper.update(dto);
    }

    public void delete(int no) {
        noticeMapper.delete(no);
    }
}
