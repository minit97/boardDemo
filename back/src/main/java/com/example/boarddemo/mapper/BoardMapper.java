package com.example.boarddemo.mapper;

import com.example.boarddemo.vo.BoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    // Create
    void createBoard(BoardVO boardVO);

    // Read
    List<BoardVO> readBoard();
    BoardVO readDetail(int b_seq);
    // Update
    void updateBoard(BoardVO boardVO);

    // Delete
    void deleteBoard(int b_seq);
}
