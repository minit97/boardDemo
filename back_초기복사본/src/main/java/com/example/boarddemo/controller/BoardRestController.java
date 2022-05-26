package com.example.boarddemo.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.boarddemo.jwt.TokenProvider;
import com.example.boarddemo.service.BoardService;
import com.example.boarddemo.vo.BoardVO;
import com.example.boarddemo.vo.MemberVO;
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
    private final TokenProvider tokenProvider;

    @PostMapping("/createBoard")
    public void createBoard(@RequestHeader("Authorization") String jwtToken,
                            @RequestPart(value = "multipartFiles", required = true) MultipartFile multipartFiles,
                            String boardInfo) {
        // 토큰 복호화 과정
        String token = jwtToken.substring(7);
        System.out.println("post에서 받은 토큰 : "+token);
        String m_seq =
                JWT.require(Algorithm.HMAC512("cos")).build().verify(token).getClaim("m_seq").asString();
        String username =
                JWT.require(Algorithm.HMAC512("cos")).build().verify(token).getClaim("username").asString();
        System.out.println("post user : "+m_seq+ " / " + username);

        // 파일 업로드 관련
        String UPLOAD_PATH = "C:\\Users\\yeoboya\\git\\boardDemo\\front\\public\\upload";
        try{
            // 객체는 client에서 직렬화하여 전달
            BoardVO boardVO = new ObjectMapper().readValue(boardInfo, BoardVO.class);
            System.out.println("boardVO : "+boardVO);


            MultipartFile file = multipartFiles;
            System.out.println("file : " + file);
            String fileId = (new Date().getTime()) + "" + (new Random().ints(1000, 9999).findAny().getAsInt()); // 현재 날짜와 랜덤 정수값으로 새로운 파일명 만들기
            String originName = file.getOriginalFilename(); // ex) 파일.jpg
            String fileExtension = originName.substring(originName.lastIndexOf(".") + 1); // ex) jpg
            originName = originName.substring(0, originName.lastIndexOf(".")); // ex) 파일
            long fileSize = file.getSize(); // 파일 사이즈

            File fileSave = new File(UPLOAD_PATH, fileId + "." + fileExtension); // ex) fileId.jpg
            if(!fileSave.exists()) { // 폴더가 없을 경우 폴더 만들기
                fileSave.mkdirs();
            }

            file.transferTo(fileSave); // fileSave의 형태로 파일 저장

//            System.out.println("fileId= " + fileId);
//            System.out.println("originName= " + originName);
//            System.out.println("fileExtension= " + fileExtension);
//            System.out.println("fileSize= " + fileSize);

            // 데이터 vo에 넣기
            boardVO.setB_fileOriginName(originName);
            boardVO.setB_fileName(fileId+"."+fileExtension);
            boardVO.setM_seq(Integer.parseInt(m_seq));

            boardService.createBoard(boardVO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/readBoard")
    public List<BoardVO> readBoard() {
        List<BoardVO> list = boardService.readBoard();
        return list;
    }

    @PutMapping("/updateBoard")
    public void updateBoard(@RequestPart(value = "multipartFiles", required = true) MultipartFile multipartFiles,
                            String boardInfo) throws JsonProcessingException {

        BoardVO boardVO = new ObjectMapper().readValue(boardInfo, BoardVO.class);
        BoardVO DBconfirm = boardService.readDetail(boardVO.getB_seq());
        System.out.println("boardVO : "+boardVO);

        String UPLOAD_PATH = "C:\\Users\\yeoboya\\git\\boardDemo\\front\\public\\upload";
        File fileConfirm = new File(UPLOAD_PATH+"\\"+DBconfirm.getB_fileName());
        if(fileConfirm.exists()) {
            fileConfirm.delete();
        }
        try{
            MultipartFile file = multipartFiles;
            System.out.println("file : " + file);
            String fileId = (new Date().getTime()) + "" + (new Random().ints(1000, 9999).findAny().getAsInt()); // 현재 날짜와 랜덤 정수값으로 새로운 파일명 만들기
            String originName = file.getOriginalFilename(); // ex) 파일.jpg
            String fileExtension = originName.substring(originName.lastIndexOf(".") + 1); // ex) jpg
            originName = originName.substring(0, originName.lastIndexOf(".")); // ex) 파일
            long fileSize = file.getSize(); // 파일 사이즈

            File fileSave = new File(UPLOAD_PATH, fileId + "." + fileExtension); // ex) fileId.jpg
            if(!fileSave.exists()) { // 폴더가 없을 경우 폴더 만들기
                fileSave.mkdirs();
            }

            file.transferTo(fileSave); // fileSave의 형태로 파일 저장

            // 데이터 vo에 넣기

            boardVO.setB_fileOriginName(originName);
            boardVO.setB_fileName(fileId+"."+fileExtension);

            boardService.updateBoard(boardVO);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @DeleteMapping("/deleteBoard")
    public void deleteBoard(@RequestBody BoardVO boardVO) {
        String UPLOAD_PATH = "C:\\Users\\yeoboya\\git\\boardDemo\\front\\public\\upload";
        File file = new File(UPLOAD_PATH+"\\"+boardVO.getB_fileName());
        if(file.exists()){
            file.delete();
        }

        boardService.deleteBoard(boardVO.getB_seq());
    }



}
