package com.example.boarddemo.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.boarddemo.mapper.MemberMapper;
import com.example.boarddemo.service.MemberService;
import com.example.boarddemo.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/selectMember")
    public MemberVO selectMember(@RequestHeader("Authorization") String jwtToken){
        String token = jwtToken.substring(7);
        System.out.println("post에서 받은 토큰 : "+token);
        String m_id =
                JWT.require(Algorithm.HMAC512("cos")).build().verify(token).getClaim("username").asString();
        MemberVO memberVO = memService.seletId(m_id);
        return memberVO;
    }

    @GetMapping("/selectAll")
    public List<MemberVO> selectAll(){
        List<MemberVO> list = memberMapper.selectAll();
        return list;
    }

    @DeleteMapping("/deleteMember")
    public void deleteMember(@RequestBody MemberVO memberVO) {
        System.out.print(memberVO.getM_seq());
        memService.deleteMember(memberVO.getM_seq());
    }
}

