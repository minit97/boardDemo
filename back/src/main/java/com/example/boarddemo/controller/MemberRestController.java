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

    @PostMapping("/member-join")
    public int joinMember(@RequestBody MemberVO mem) {
        int check = memService.memberJoin(mem);
        return check;
    }

    @GetMapping("/member-one")
    public MemberVO selectMember(@RequestHeader("Authorization") String jwtToken){
        // 토큰의 담긴 memberId 값으로 유저 정보 반환
        MemberVO memberVO = memService.selectIdForToken(jwtToken);
        return memberVO;
    }

    @DeleteMapping("/member")
    public void deleteMember(@RequestBody MemberVO memberVO) {
        memService.deleteMember(memberVO.getM_seq());
    }

    @GetMapping("/members")
    public List<MemberVO> selectAll(){
        List<MemberVO> list = memberMapper.selectAll();
        return list;
    }
}

