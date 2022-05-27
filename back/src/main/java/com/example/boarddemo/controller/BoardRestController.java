package com.example.boarddemo.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.boarddemo.service.BoardService;
import com.example.boarddemo.vo.BoardVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequiredArgsConstructor
public class BoardRestController {
    private final BoardService boardService;

    @PostMapping("/board")
    public void createBoard(@RequestHeader("Authorization") String jwtToken,
                            @RequestPart(value = "multipartFiles", required = true) MultipartFile multipartFiles,
                            String boardInfo) {
        boardService.createBoard(jwtToken, boardInfo, multipartFiles);
    }

    @GetMapping("/board-list")
    public List<BoardVO> readBoard() {
        List<BoardVO> list = boardService.readBoard();
        return list;
    }

    @PutMapping("/board")
    public void updateBoard(@RequestPart(value = "multipartFiles", required = true) MultipartFile multipartFiles,
                            String boardInfo) {
        boardService.updateBoard(boardInfo, multipartFiles);
    }

    @DeleteMapping("/board")
    public void deleteBoard(@RequestBody BoardVO boardVO) {
        boardService.deleteBoard(boardVO);
    }


}
