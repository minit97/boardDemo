import React, { useEffect, useState, useCallback } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "./Main.scss";

import Header from "./components/Header";
// import Profile from "./components/Profile";
import Board from "./components/Board";
import Footer from "./components/Footer";
import CUModal from "./components/Modal/CUModal";

const Main = (props) => {
  const navigate = useNavigate();
  const [createModal, setCreateModal] = useState(false);
  const [res, setRes] = useState([]);
  const [userRes, setUserRes] = useState();
  const [Rerender, setRerender] = useState(false);
  const [logExist, setLogExist] = useState(false);
  const RerenderFunc = () => {
    if (Rerender === false) {
      setRerender(true);
    } else {
      setRerender(false);
    }
  };
  useEffect(() => {
    if (localStorage.getItem("jwtToken") === null) {
      setLogExist(false);
    } else {
      setLogExist(true);
    }
  }, [setLogExist]);

  const createModalHandler = () => {
    if (logExist === true) {
      if (createModal === false) {
        setCreateModal(true);
      } else {
        setCreateModal(false);
      }
    } else {
      navigate("/");
    }
  };

  const boardGet = useCallback(async () => {
    try {
      const response = await axios.get("/readBoard");
      setRes(response.data);
    } catch (error) {
      console.log("error", error);
    }
  }, []);

  const userInfo = useCallback(async () => {
    try {
      const response = await axios.get("/selectMember", {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("jwtToken"),
        },
      });
      setUserRes(response.data);
    } catch (error) {
      console.log("error", error);
    }
  }, []);

  useEffect(() => {
    boardGet();

    if (logExist === true) {
      userInfo();
    }
  }, [boardGet, userInfo, logExist, createModal, Rerender]);

  return (
    <>
      <Header logExist={logExist} userRes={userRes} />
      <main className="main">
        <div className="contents">
          {/* <Profile /> */}
          <div className="btnWrap">
            {createModal && (
              <CUModal
                cancelModalHandler={createModalHandler}
                userRes={userRes}
              />
            )}

            <button className="createBtn" onClick={createModalHandler}>
              게시글 작성
            </button>
          </div>
          {res !== null && (
            <Board data={res} RerenderFunc={RerenderFunc} userRes={userRes} />
          )}
          <Footer />
        </div>
      </main>
    </>
  );
};

export default Main;
