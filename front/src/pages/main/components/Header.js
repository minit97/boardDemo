import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Header.scss";
import logo from "../../../img/intaLogo.png";
import UserListModal from "./Modal/UserListModal";

const Header = (props) => {
  const { logExist, userRes } = props;
  const navigate = useNavigate();
  const [admin, setAdmin] = useState(false);
  const [userListModal, setUserListModal]= useState(false);
  const logoutHandler = () => {
    localStorage.removeItem("jwtToken");
    navigate("/");
  };

  useEffect(() => {
    if (userRes !== undefined) {
      if (userRes.m_role === "ROLE_ADMIN") {
        setAdmin(true);
      }
    }
  }, [userRes]);

  const UserListModalHandler = () => {
    if(userListModal === false){
      setUserListModal(true);
    }else{
      setUserListModal(false);
    }
  }
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
          {userListModal && <UserListModal UserListModalHandler={UserListModalHandler}/>}
          {admin && <button className="admin" onClick={UserListModalHandler}>관리자 권한</button>}
          <button className="logout" onClick={logoutHandler}>
            {logExist ? "로그아웃" : "로그인"}
          </button>
        </div>
      </div>
    </header>
  );
};

export default Header;
