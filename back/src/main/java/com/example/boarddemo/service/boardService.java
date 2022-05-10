package com.example.boarddemo.service;

import com.example.boarddemo.mapper.boardMapper;

import com.example.boarddemo.vo.boardVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class boardService {
    private final boardMapper boardMapper;

    public void createBoard(boardVO boardVO) {
        boardMapper.createBoard(boardVO);
    }


}
