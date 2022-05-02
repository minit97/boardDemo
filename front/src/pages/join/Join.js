import React from "react";

const Join = (props) => {

    return (
        <div className="loginContainer">
      <div className="loginWrap">
        <div className="logoWrap"></div>
        <form className="formWrap">
          <label className="id">
            <input type="text" placeholder="아이디를 입력하세요." />
          </label>
          <label className="pw">
            <input type="password" placeholder="비밀번호"/>
          </label>
          <label className="name">
            <input type="text" placeholder="이름"/>
          </label>
          <label className="tel">
            <input type="text" placeholder="전화번호"/>
          </label>
          <label className="email">
            <input type="email" placeholder="이메일"/>
          </label>
          <button className="loginBtn">가입</button>
        </form>
      </div>
      <div className="joinWrap">
        <p className="jointext">
          계정이 있으신가요? <a href="#!">로그인</a>
        </p>
      </div>
    </div>
    );
};

export default Join;