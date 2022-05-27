package com.example.boarddemo.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.boarddemo.config.auth.PrincipalDetails;
import com.example.boarddemo.vo.MemberVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

// 스프링 시큐리티에서 UsernamePasswordAuthenticationFilter 가 있음
// /login 요청해서 username, password 전송하면 (post)
// UsernamePasswordAuthenticationFilter 동작함

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    // /login요청을 하면 로그인 시도를 위해서 실행되는 함수수
   @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {


       // 1. username,password 받아서
       try {

           // json데이터를 parsing해줌
           ObjectMapper om = new ObjectMapper();
           MemberVO mem = om.readValue(request.getInputStream(), MemberVO.class);
           System.out.println("request요청 값들 :"+mem);

           UsernamePasswordAuthenticationToken authenticationToken =
                   new UsernamePasswordAuthenticationToken(mem.getM_id(),mem.getM_pw());

           // PrincipalDetailsService의 loadUserByUsername() 함수가 실행된 후 정상이면 authentication이 리턴
           // DB에 있는 username과 password가 일치한다.
           // pw는 스프링이 알아서 처리 해줌
           Authentication authentication =
                   authenticationManager.authenticate(authenticationToken);

           // => 로그인이 되었다는 뜻
           PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            System.out.println("로그인 완료 여부: "+principalDetails.getUsername());
           // authentication 객체가 session영역에 저장을 해야하고 그 방법이 return 해주면 됨
           // 리턴의 이유는 권한 관리를 security가 대신 해주기 떄문에 편하려고 하는 거임
           // 굳이 JWT토큰을 사용하면서 세션을 만들 이유가 없음. 근데 단지 권한 처리때문에 session넣어줌
            return  authentication;
       } catch (IOException e) {
           e.printStackTrace();
           System.out.println(111111);
       }
       return null;
    }


    // attemptAuthentication 실행 후 인증이 정상적으로 되었으면 successfulAuthentication 함수 실행
    // JWT 토큰을 만들어서 request요청한 사용자에게 JWT토큰을 response해주면 됨
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult)
            throws IOException, ServletException {
        System.out.println("successfulAuthentication 실행됨 : 인증완료되었다는 뜻");
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();
        
        // RSA방식은 아니구 Hash암호방식
        String jwtToken = JWT.create()
                .withSubject("cos토큰")
                .withExpiresAt(new Date(System.currentTimeMillis()+(60000*100000)))
                .withClaim("username",principalDetails.getUsername())
                .withClaim("m_seq", Integer.toString(principalDetails.getMem().getM_seq()))
                .sign(Algorithm.HMAC512("cos"));

       response.addHeader("Authorization","Bearer "+jwtToken);
    }
}
