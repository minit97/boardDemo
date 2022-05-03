import React from "react";
import { useNavigate } from "react-router-dom";
import "./Login.scss";

const Login = (props) => {
  const navigate = useNavigate();
  return (
    <div className="loginContainer">
      <div className="loginWrap">
        <div className="logoWrap"></div>
        <form className="formWrap">
          <label className="id">
            <input type="text" placeholder="아이디를 입력하세요." />
          </label>
          <label className="pw">
            <input type="password" placeholder="비밀번호" />
          </label>
          <button className="loginBtn">로그인</button>
        </form>
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
