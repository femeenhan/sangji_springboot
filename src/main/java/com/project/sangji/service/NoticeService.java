package com.project.sangji.service;

import com.project.sangji.mapper.NoticeMapper;
import com.project.sangji.model.NoticeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeMapper noticeMapper;

    public List<NoticeDTO> selectAll(){
        return noticeMapper.selectAll();
    }
}
