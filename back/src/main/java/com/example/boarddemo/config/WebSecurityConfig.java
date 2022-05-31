package com.example.boarddemo.config;

import com.example.boarddemo.config.jwt.JwtAuthenticationFilter;
import com.example.boarddemo.config.jwt.JwtAuthorizationFilter;
import com.example.boarddemo.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.filter.CorsFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private final CorsFilter corsFilter;
    private final MemberMapper memberMapper;


    // 패스워드 인코더
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()   // jwt토큰 방식을 쓰기에 필요한 설정

                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)        // 세션을 사용하지 않기에 stateless

                .and()
                .addFilter(corsFilter)      // @CrossOrigin(인증X), 시큐리티 필터에 등록 인증(O)
                .formLogin().disable()      // 폼로그인 사용X
                .httpBasic().disable()      // Bearer 방식을 쓸거기 때문
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))   // AuthenticationManager
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), memberMapper))
                .authorizeRequests()
                .antMatchers("/member-join", "/board-list").permitAll()                       //login페이지
                .anyRequest().authenticated();

    }

}






