package com.example.boarddemo.controller;

import com.example.boarddemo.mapper.memberMapper;
import com.example.boarddemo.service.memberService;
import com.example.boarddemo.vo.memberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class memberRestController {

    private final memberService memService;
    private final memberMapper memberMapper;

    @PostMapping("/joinMember")
    public int joinMember(@RequestBody memberVO mem) {
        int check = memService.memberJoin(mem);
        return check;
    }
}

