package com.example.boarddemo.mapper;

import com.example.boarddemo.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    // 회원가입
    int memberJoin(MemberVO mem);
    // 로그인
    void memberLogin(MemberVO mem);
    // 유저검색
    MemberVO selectId(String m_id);
    // 유저전체검색
    List<MemberVO> selectAll();
    // 유저삭제
    void deleteMember(int m_seq);
}
