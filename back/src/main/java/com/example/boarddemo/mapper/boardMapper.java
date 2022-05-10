package com.example.boarddemo.mapper;

import com.example.boarddemo.vo.boardVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface boardMapper {
    // Create
    void createBoard(boardVO boardVO);
}
