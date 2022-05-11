package com.example.boarddemo.mapper;

import com.example.boarddemo.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    // 회원가입
    int memberJoin(MemberVO mem);
    // 로그인
    void memberLogin(MemberVO mem);
    // 유저검색
    MemberVO selectId(String m_id);
    // 권한부여
//    void roleInsert(roleVO role);

}
