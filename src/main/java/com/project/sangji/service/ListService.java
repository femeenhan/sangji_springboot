package com.project.sangji.service;

import com.project.sangji.common.Pagination;
import com.project.sangji.mapper.ListMapper;
import com.project.sangji.model.BoardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListService {
    private final ListMapper listMapper;

    public List<BoardDTO> selectAll(Pagination pg) {

        return listMapper.selectAll(pg);
    }

    public Integer totalCount(Pagination pg) {
        Integer count = listMapper.totalCount(pg);
        return count != null ? count : 0;
    }
}
