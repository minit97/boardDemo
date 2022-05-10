package com.example.boarddemo.mapper;

import com.example.boarddemo.vo.memberVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface memberMapper {
    // 회원가입
    int memberJoin(memberVO mem);
    // 로그인
    void memberLogin(memberVO mem);
    // 유저검색
    memberVO selectId(String m_id);
    // 권한부여
//    void roleInsert(roleVO role);

}
