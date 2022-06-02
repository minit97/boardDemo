package com.example.boarddemo.config.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.boarddemo.config.auth.PrincipalDetails;
import com.example.boarddemo.mapper.MemberMapper;
import com.example.boarddemo.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {




    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("인터셉터");

        String jwtHeader = request.getHeader("Authorization");
        System.out.println("jwtHeader : " + jwtHeader);

        // header가 있는 확인
        if (jwtHeader == null || jwtHeader.trim().isEmpty() || !jwtHeader.startsWith("Bearer")) {
            System.out.println("헤더없음");
        } else {

            // JWT 토큰을 검증을 해서 정상적인 사용자인지 확인
            String jwtToken = request.getHeader("Authorization").replace("Bearer ", "");
            System.out.println("jwtToken = " + jwtToken);
            String username =
                    JWT.require(Algorithm.HMAC512("cos")).build().verify(jwtToken).getClaim("username").asString();

            System.out.println("토큰 유저네임 : " +username);
            // 서명이 정상적으로 됨
//            if (username != null) {
//                MemberVO mem = memberMapper.selectId(username);
//
//                PrincipalDetails principalDetails = new PrincipalDetails(mem);
//                // Jwt 토큰 서명을 통해서 서명이 정상이면 Authentication 객체를 만들어준다.
//                Authentication authentication =
//                        new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
//
//                // 강제로 시큐리티의 세션에 접근하여 Authentication 객체를 저장.
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            }
        }
        return true;
    }
}
