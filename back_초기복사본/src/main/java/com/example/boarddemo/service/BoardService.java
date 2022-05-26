package com.example.boarddemo.service;

import com.example.boarddemo.mapper.BoardMapper;

import com.example.boarddemo.vo.BoardVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper boardMapper;

    // Create
    public void createBoard(BoardVO boardVO) {
        boardMapper.createBoard(boardVO);
    }
    // Read
    public List<BoardVO> readBoard(){
        return boardMapper.readBoard();
    }
    public BoardVO readDetail(int b_seq){
        return boardMapper.readDetail(b_seq);
    };
    // Update
    public void updateBoard(BoardVO boardVO){
        boardMapper.updateBoard(boardVO);
    }
    // Delete
    public void deleteBoard(int b_seq){
        boardMapper.deleteBoard(b_seq);
    }


}
