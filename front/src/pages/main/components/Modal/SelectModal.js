import React from "react";

import './SelectModal.scss';

const SelectModal = (props) => {
  const {cancelModalHandler} = props;
  return (
    <div className="modal1">
      <div className="modalOverlay1" onClick={cancelModalHandler}></div>
      <div className="modalContent1">
        <div className="selectModalWrap">
          <button className="selectBtn del">삭제</button>
          <button className="selectBtn">수정</button>
          <button className="selectBtn" onClick={cancelModalHandler}>취소</button>
        </div>
      </div>
    </div>
  );
};

export default SelectModal;
