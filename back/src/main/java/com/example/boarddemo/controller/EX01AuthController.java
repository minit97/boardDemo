// 본 파일은 사용하지않고 코드 이해용으로 정리해두는 파일입니다.
package com.example.boarddemo.controller;

import com.example.boarddemo.jwt.JwtFilter;
import com.example.boarddemo.jwt.TokenProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class EX01AuthController {
    /*
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public AuthController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder){
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder
    }

    @PostMapping("/authenticate")
    public RespinseEntity<TokenDto> authorize(@Valid @RequestBody LoginDto loginDto){

        // user, pw를 받고 Authentication객체 생성
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        // Authentication 객체 생성 후 SecurityContext에 저장 후
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // jwt 토큰 생성
        String jwt = tokenProvider.createToken(authentication);

        // jwt토큰을 Response Header에 넣어준다.
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer"+jwt);

        // TokenDto를 이용해서 Response Body에도넣어서 리턴턴
       return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
    }

    */
}

