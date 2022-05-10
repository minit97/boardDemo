package com.example.boarddemo.controller;

import com.auth0.jwt.JWT;
import com.example.boarddemo.service.boardService;
import com.example.boarddemo.vo.boardVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

@RestController
@RequiredArgsConstructor
public class boardRestController {
    private final boardService boardService;

    @PostMapping("/createBoard")
    public String loginMember(@RequestBody boardVO boardVO, HttpServletRequest request) {
        System.out.println(boardVO);
        boardService.createBoard(boardVO);
        return "잘들옴";
    }






/*
    @PostMapping("/createBoard2")
    public void loginMember(MultipartFile multipartFiles, String stringboardVO) {
        String UPLOAD_PATH = "C:\\Users\\yeoboya\\Desktop\\crud";

        try{
            // 객체는 client에서 직렬화하여 전달
            boardVO boardVO = new ObjectMapper().readValue(stringboardVO, boardVO.class);
            System.out.println("boardVO"+boardVO);


                MultipartFile file = multipartFiles;

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

                System.out.println("fileId= " + fileId);
                System.out.println("originName= " + originName);
                System.out.println("fileExtension= " + fileExtension);
                System.out.println("fileSize= " + fileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/
}
