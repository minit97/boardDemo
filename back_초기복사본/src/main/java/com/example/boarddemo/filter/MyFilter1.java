package com.example.boarddemo.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyFilter1 implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("필터1");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // 토큰 : cos 이걸 만들어줘야함. id,pw 정상적으로 들어와서 로그인이 완료되면 토큰을 만들어주고 그걸 응답해준다.
        // 요청할 때 마다 header에 Authorization에 value값으로 토큰을 가지고 오겠지?
        // 그때 토큰이 넘어오면 이 토큰이 내가 만든 토큰이 맞는지만 검증 ( RSA, HS256 )
        if(req.getMethod().equals("POST")){
            System.out.println("POST요청됨");
            String headerAuth = req.getHeader("Authorization");
            System.out.println(headerAuth);

            if(headerAuth !=null &&headerAuth.equals("cos")){
                chain.doFilter(req,res);   // 체인에 넘겨줘야 끝나지 않음.
            }else{
                System.out.println("인증실패");
            }
        }



    }
}
