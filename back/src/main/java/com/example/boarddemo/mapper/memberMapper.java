package com.example.boarddemo.mapper;

import com.example.boarddemo.vo.memberVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface memberMapper {
    // 회원가입
    int memberJoin(memberVO mem);

}
