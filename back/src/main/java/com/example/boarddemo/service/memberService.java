package com.example.boarddemo.service;




import com.example.boarddemo.mapper.memberMapper;
import com.example.boarddemo.vo.memberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class memberService {

    private final memberMapper memMapper;
    private final PasswordEncoder passwordEncoder;

    public int memberJoin(memberVO mem){
        String encodedPassword = passwordEncoder.encode(mem.getM_pw());
        mem.setM_pw(encodedPassword);
        mem.setM_enabled(true);
        mem.setM_role("ROLE_USER");

        return memMapper.memberJoin(mem);
    };
}
