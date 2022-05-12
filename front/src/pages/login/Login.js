import React, { useRef, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

import "./Login.scss";

const Login = (props) => {
  const navigate = useNavigate();
  const idRef = useRef("");
  const pwRef = useRef("");
  const [loginValidation, setLoginValidation] = useState(true);

  const loginPost = async (data) => {
    try {
      const response = await axios.post("/login", data);
      const resToken = await response.headers.authorization;
      return resToken;
    } catch (error) {
      console.log("error", error);
      return null;
    }
  };

  const loginSubmit = async (event) => {
    event.preventDefault();
    const loginInfo = {
      m_id: idRef.current.value,
      m_pw: pwRef.current.value,
    };

    const res = await loginPost(loginInfo);    
    if (res !== null) {
      localStorage.setItem('jwtToken',res.replace("Bearer ",""));
              
      navigate("/main");
    } else {
      setLoginValidation(false);
    }
  };

  const goMainHandler = () => {
    navigate("/main");
  };

  return (
    <div className="loginContainer">
      <div className="loginWrap">
        <div className="logoWrap"></div>
        <form className="formWrap" onSubmit={loginSubmit}>
          <label className="id">
            <input ref={idRef} type="text" placeholder="아이디를 입력하세요." />
          </label>
          <label className="pw">
            <input ref={pwRef} type="password" placeholder="비밀번호" autoComplete="on"/>
          </label>
          <button className="loginBtn">로그인</button>
        </form>
        {!loginValidation && <div className="loginFail">로그인 실패</div>}
        <button className="goMain" onClick={goMainHandler}>메인으로 가기</button>
      </div>
      <div className="joinWrap">
        <p className="jointext">
          계정이 없으신가요?{" "}
          <a
            href="#!"
            onClick={(event) => {
              event.preventDefault();
              navigate("/join");
            }}
          >
            가입하기
          </a>
        </p>
      </div>
    </div>
  );
};

export default Login;
