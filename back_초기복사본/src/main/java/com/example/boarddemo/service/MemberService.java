package com.example.boarddemo.service;




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

    public int memberJoin(MemberVO mem){
        String encodedPassword = passwordEncoder.encode(mem.getM_pw());
        mem.setM_pw(encodedPassword);
        mem.setM_enabled(true);
        mem.setM_role("ROLE_USER");

        return memMapper.memberJoin(mem);
    };

    public MemberVO seletId(String m_id){
        return memMapper.selectId(m_id);
    }

    public List<MemberVO> selectAll(){
        return memMapper.selectAll();
    }

    public void deleteMember(int m_seq){
      memMapper.deleteMember(m_seq);
    };
}
