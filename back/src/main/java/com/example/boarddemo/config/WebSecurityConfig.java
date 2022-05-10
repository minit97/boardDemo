package com.example.boarddemo.config;

import com.example.boarddemo.config.jwt.JwtAuthenticationFilter;
import com.example.boarddemo.config.jwt.JwtAuthorizationFilter;
import com.example.boarddemo.filter.MyFilter1;
import com.example.boarddemo.jwt.JwtAccessDeniedHandler;
import com.example.boarddemo.jwt.JwtAuthenticationEntryPoint;
import com.example.boarddemo.jwt.JwtSecurityConfig;
import com.example.boarddemo.jwt.TokenProvider;
import com.example.boarddemo.mapper.memberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.CorsFilter;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
// securedEnabled = true : @Secured("ROLE_ADMIN")
// prePostEnabled = true : @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    private final CorsFilter corsFilter;
    private final memberMapper memberMapper;

    public WebSecurityConfig(TokenProvider tokenProvider,
                             JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
                             JwtAccessDeniedHandler jwtAccessDeniedHandler,
                             CorsFilter corsFilter,
                             memberMapper memberMapper) {
        this.tokenProvider = tokenProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
        this.corsFilter = corsFilter;
        this.memberMapper = memberMapper;
    }

    // 패스워드 인코더
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.addFilterBefore(new MyFilter1(), BasicAuthenticationFilter.class)  // BasicAuthenticationFilter전에 필터를 건다.
                .csrf().disable()   // jwt토큰 방식을 쓰기에 필요한 설정

                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)        // 세션을 사용하지 않기에 stateless

                .and()
                .addFilter(corsFilter)      // @CrossOrigin(인증X), 시큐리티 필터에 등록 인증(O)
                .formLogin().disable()      // 폼로그인 사용X
                .httpBasic().disable()      // Bearer 방식을 쓸거기 때문
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))   // AuthenticationManager
                .addFilter(new JwtAuthorizationFilter(authenticationManager(),memberMapper))
                .authorizeRequests()
                .antMatchers("/joinMember").permitAll()                       //login페이지
                .anyRequest().authenticated();

                /*
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                .and()
                .apply(new JwtSecurityConfig(tokenProvider));                  // JwtFilter를 addFilterBefore로 등록했던 JwtSecurityConfig 클래스도 적용
                */
    }

}





/* ----------------------------------------------------------------------------------------------------------

 * 용어정리
 *      Authentication : 로그인
 *      Authorization : 권한
 *
 * (JPA에 JOIN이 들어가면 복잡해짐)
 * JPA 매핑
 *      @OneToOne       ex) user-user_detail
 *      @OneToMany      ex) user-board
 *      @ManyToOne      ex) board-user
 *      @ManyToMany     ex) user-role   -> 연결해주는 맵핑 테이블 설계
 * */