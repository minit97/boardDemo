package com.example.boarddemo.config;

import com.example.boarddemo.jwt.JwtAccessDeniedHandler;
import com.example.boarddemo.jwt.JwtAuthenticationEntryPoint;
import com.example.boarddemo.jwt.JwtSecurityConfig;
import com.example.boarddemo.jwt.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)      // @PreAuthorize를 메소드 단위로 추가하기위해서 적요
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    public WebSecurityConfig(TokenProvider tokenProvider,JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint ,JwtAccessDeniedHandler jwtAccessDeniedHandler){
        this.tokenProvider = tokenProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }

    // 패스워드 인코더
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws  Exception{
        http
                .csrf().disable()   // jwt토큰 방식을 쓰기에 필요한 설정

                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)        // 세션을 사용하지 않기에 stateless

                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()                       //login페이지
                .antMatchers("/join").permitAll()
                .anyRequest().authenticated()

                .and()
                .apply(new JwtSecurityConfig(tokenProvider));                  // JwtFilter를 addFilterBefore로 등록했던 JwtSecurityConfig 클래스도 적용
    }
    
    
    // jdbc 인증 설정
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("select m_id,m_pw,m_enabled "
                        + "from member "
                        + "where m_id = ?")
                .authoritiesByUsernameQuery("select email, authority "
                        + "from mem_role ur inner join member u  on "
                        + "where email = ?");
    }



}



/* ----------------------------------------------------------------------------------------------------------
                .authorizeHttpRequests()
                .mvcMatchers(HttpMethod.OPTIONS, "/**").permitAll();
    security
            authorizeRequests : HttpServletRequest를 사용하는 요청들에 대한 접근제한을 설정
            antMatchers : 해당 요청은 인증없이 접근 허용
            anyRequest().authenticated() : 나머지 요청들은 인증 받아야한다.

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