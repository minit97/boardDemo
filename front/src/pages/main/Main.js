import React from "react";

import "./Main.scss";

import Header from "./components/Header";
import Profile from "./components/Profile";
import Board from "./components/Board";
import Footer from "./components/Footer";

const Main = (props) => {
  return (
    <>
      <Header />
      <main className="main">
        <div className="contents">
          <Profile />
          <div className="btnWrap">
              <button className="createBtn">게시글 작성</button>
          </div>
          <Board />
          <Footer />
        </div>
      </main>
    </>
  );
};

export default Main;
