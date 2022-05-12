import React, { useState } from "react";
import "./Board.scss";
import ReadModal from "./Modal/ReadModal";

const BoardList = (props) => {
  const { data, RerenderFunc,userRes } = props;
  const [readModal, setReadModal] = useState(false);

  const readModalHandler = () => {
    if (readModal === false) {
      setReadModal(true);
    } else {
      setReadModal(false);
    }
  };
  return (
    <div className="listImg">
      {readModal && (
        <ReadModal
          cancelModalHandler={readModalHandler}
          data={data}
          RerenderFunc={RerenderFunc}
          userRes={userRes}
        />
      )}
      <div className="innerBox" onClick={readModalHandler}>
        <img src={`/upload/${data.b_fileName}`} alt="" />
        <div className="imgInfo">
          <div className="playCount">
            <div className="playIcon"></div>
            3.6백만
          </div>
          <div className="commentCount">
            <div className="commentIcon"></div>
            9,876
          </div>
        </div>
      </div>
    </div>
  );
};

export default BoardList;
