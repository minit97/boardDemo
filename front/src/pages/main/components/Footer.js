import React from "react";

import "./Footer.scss";

const Footer = (props) => {
  return (
    <footer className="footer">
      <ul className="footHelp">
        <li>Meta</li>
        <li>소개</li>
        <li>블로그</li>
        <li>채용 정보</li>
        <li>도움말</li>
        <li>API</li>
        <li>개인정보처리방침</li>
        <li>약관</li>
        <li>인기 계정</li>
        <li>해시태그</li>
        <li>위치</li>
        <li>Instagram Lite</li>
        <li>추천 계정</li>
      </ul>
      <div className="lang">
        <div>한국어</div>
        <div>Ⓒ 2022 Instagram from Meta</div>
      </div>
    </footer>
  );
};

export default Footer;
