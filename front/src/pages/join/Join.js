import axios from "axios";
import React, { useRef } from "react";
import { useNavigate } from "react-router-dom";

const Join = (props) => {
  const navigate = useNavigate();
  const idRef = useRef("");
  const pwRef = useRef("");
  const nameRef = useRef("");
  const tellRef = useRef("");
  const emailRef = useRef("");

  const joinSubmit = (event) => {
    event.preventDefault();

    const joinInfo = {
      m_seq: "",
      m_id: idRef.current.value,
      m_pw: pwRef.current.value,
      m_name: nameRef.current.value,
      m_tell: tellRef.current.value,
      m_email: emailRef.current.value,
    };

    joinPost(joinInfo).then(result=>{
      if(result===1){
        navigate("/");
      }else{
          alert("회원가입 실패");
      }
    });

    idRef.current.value = '';
    pwRef.current.value = '';
    nameRef.current.value = '';
    tellRef.current.value = '';
    emailRef.current.value = '';
  };

  const joinPost = async (data) => {
    try {
      const response = await axios.post("/joinMember", data);
      // console.log("res", response.data);
      return response.data;
    } catch (error) {
      console.log("error", error);
      return 0;
    }
  };

  return (
    <div className="loginContainer">
      <div className="loginWrap">
        <div className="logoWrap"></div>
        <form className="formWrap" onSubmit={joinSubmit}>
          <label className="id">
            <input type="text" placeholder="아이디를 입력하세요." ref={idRef} />
          </label>
          <label className="pw">
            <input type="password" placeholder="비밀번호" ref={pwRef} />
          </label>
          <label className="name">
            <input type="text" placeholder="이름" ref={nameRef} />
          </label>
          <label className="tel">
            <input type="text" placeholder="전화번호" ref={tellRef} />
          </label>
          <label className="email">
            <input type="email" placeholder="이메일" ref={emailRef} />
          </label>
          <button className="loginBtn">가입</button>
        </form>
      </div>
      <div className="joinWrap">
        <p className="jointext">
          계정이 있으신가요?{" "}
          <a
            href="#!"
            onClick={(event) => {
              event.preventDefault();
              navigate("/");
            }}
          >
            로그인
          </a>
        </p>
      </div>
    </div>
  );
};

export default Join;
