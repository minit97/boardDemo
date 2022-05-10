import React,{ useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Main.scss";

import Header from "./components/Header";
import Profile from "./components/Profile";
import Board from "./components/Board";
import Footer from "./components/Footer";
import CUModal from "./components/Modal/CUModal";

const Main = (props) => {
  // console.log("token",localStorage.getItem('jwtToken'));
  const navigate = useNavigate();
  const [createModal, setCreateModal] = useState(false);

  useEffect(()=>{
    if(localStorage.getItem('jwtToken')===null){
      navigate("/");
    }
  },[navigate]);
  
  const createModalHandler = () => {
    if(createModal === false){
      setCreateModal(true);
    }else{
      setCreateModal(false);
    }
  };

  return (
    <>
      <Header />
      <main className="main">
        <div className="contents">
          <Profile />
          <div className="btnWrap">
              {createModal && <CUModal cancelModalHandler={createModalHandler}/>}
              <button className="createBtn" onClick={createModalHandler}>게시글 작성</button>
          </div>
          <Board />
          <Footer />
        </div>
      </main>
    </>
  );
};

export default Main;
