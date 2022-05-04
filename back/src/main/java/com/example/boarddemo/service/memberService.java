package com.example.boarddemo.service;




import com.example.boarddemo.mapper.memberMapper;
import com.example.boarddemo.vo.memberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class memberService {

    private final memberMapper memMapper;

    public int memberJoin(memberVO mem){
        return memMapper.memberJoin(mem);
    };
}
