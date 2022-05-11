package com.example.boarddemo.controller;

import com.example.boarddemo.mapper.MemberMapper;
import com.example.boarddemo.service.MemberService;
import com.example.boarddemo.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberRestController {

    private final MemberService memService;
    private final MemberMapper memberMapper;

    @PostMapping("/joinMember")
    public int joinMember(@RequestBody MemberVO mem) {
        int check = memService.memberJoin(mem);
        return check;
    }
}

