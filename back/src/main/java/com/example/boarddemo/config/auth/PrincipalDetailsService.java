package com.example.boarddemo.config.auth;

import com.example.boarddemo.mapper.memberMapper;
import com.example.boarddemo.vo.memberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// http://localhost:8080/login

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final memberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername 로그인요청");
        memberVO user = memberMapper.selectId(username);
        return new PrincipalDetails(user);
    }
}
