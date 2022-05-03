import React from "react";

import "./Modal.scss";

const Modal = (props) => {
  return (
<div className="modal">
            <div className="modalOverlay"></div>
            <div className="modalContent">
              <h2>로그인하여 Instagram에서 여러분의 세상과 연결해보세요</h2>
              <div className="loginBox">
                <div className="closeIcon"></div>
                <div className="instaIcon"></div>
                <form className="formContainer">
                  <label className="id">
                    <input
                      type="text"
                      placeholder="전화번호,사용자 이름 또는 이메일"
                    />
                  </label>
                  <label className="pw">
                    <input type="password" placeholder="비밀번호" />
                  </label>
                  <button>로그인</button>
                </form>
                <div className="orBox">또는</div>
                <div className="byFacebook">
                  <div className="facebookIcon"></div>
                  Facebook으로 로그인
                </div>
                <div className="lostPassword">비밀번호를 잊으셨나요?</div>
                <div className="doJoin">계정없으신가요? <a href="#!">가입하기</a></div>
              </div>
            </div>
          </div>
  );
};

export default Modal;
