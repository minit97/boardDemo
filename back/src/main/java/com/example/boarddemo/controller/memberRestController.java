package com.example.boarddemo.controller;

import com.example.boarddemo.service.memberService;
import com.example.boarddemo.vo.memberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class memberRestController {
    @Autowired
    private memberService memService;

    @PostMapping("/joinMember")
    public int joinMember(@RequestBody memberVO mem) {
        int a = memService.memberJoin(mem);
        System.out.println(a);
        return a;
    }

    @PostMapping("/loginMember")
    public void loginMember(){
    }

}
