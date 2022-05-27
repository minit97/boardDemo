package com.example.boarddemo.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.boarddemo.mapper.MemberMapper;
import com.example.boarddemo.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memMapper;
    private final PasswordEncoder passwordEncoder;

    public int memberJoin(MemberVO mem) {
        String encodedPassword = passwordEncoder.encode(mem.getM_pw());
        mem.setM_pw(encodedPassword);
        mem.setM_enabled(true);
        mem.setM_role("ROLE_USER");

        return memMapper.memberJoin(mem);
    };

    public MemberVO selectIdForToken(String jwtToken) {
        String token = jwtToken.substring(7);
        String m_id = JWT.require(Algorithm.HMAC512("cos")).build().verify(token).getClaim("username").asString();
        return memMapper.selectId(m_id);
    };

    public List<MemberVO> selectAll() {
        return memMapper.selectAll();
    };

    public void deleteMember(int m_seq) {
        memMapper.deleteMember(m_seq);
    };


}
