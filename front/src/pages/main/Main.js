import React, { useEffect, useState, useCallback  } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "./Main.scss";

import Header from "./components/Header";
// import Profile from "./components/Profile";
import Board from "./components/Board";
import Footer from "./components/Footer";
import CUModal from "./components/Modal/CUModal";

const Main = (props) => {
  // console.log("token",localStorage.getItem('jwtToken'));
  const navigate = useNavigate();
  const [createModal, setCreateModal] = useState(false);
  const [res,setRes] = useState([]);
  const [delRerender, setDelRerender] = useState(false);

  useEffect(() => {
    if (localStorage.getItem("jwtToken") === null) {
      navigate("/");
    }
  }, [navigate]);

  const createModalHandler = () => {
    if (createModal === false) {
      setCreateModal(true);
    } else {
      setCreateModal(false);
    }
  };
  
  const boardGet = useCallback (async () => {
    try {
      const response = await axios.get("/readBoard", {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("jwtToken"),
        },
      });
      setRes(response.data);
      // console.log(response.data);
    } catch (error) {
      console.log("error", error);
    }
  },[]);

  useEffect(() => {
    boardGet();
  },[boardGet,createModal,delRerender]);

  return (
    <>
      <Header />
      <main className="main">
        <div className="contents">
          {/* <Profile /> */}
          <div className="btnWrap">
            {createModal && <CUModal cancelModalHandler={createModalHandler} />}
            <button className="createBtn" onClick={createModalHandler}>
              게시글 작성
            </button>
          </div>
          {res !==null && <Board data={res} setDelRerender={setDelRerender}/>}
          <Footer />
        </div>
      </main>
    </>
  );
};

export default Main;
