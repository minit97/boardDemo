import React from "react";

import "./Header.scss";
import logo from "../../../img/intaLogo.png";


const Header = (props) => {
  return (
    <header className="header">
      <div className="headerWrap">
        <div className="logo">
          <img src={logo} alt="logo" />
        </div>
        <label className="search">
          <div className="searchIcon"></div>
          <input type="text" placeholder="검색" />
        </label>
        <div className="buttonContainer">
       <button className="admin">관리자 권한</button>
          <button className="logout">로그아웃</button>
        </div>
      </div>
    </header>
  );
};

export default Header;
