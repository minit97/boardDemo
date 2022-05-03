import React from "react";

import './SelectModal.scss';

const SelectModal = () => {
  return (
    <div className="modal1">
      <div className="modalOverlay1"></div>
      <div className="modalContent1">
        <div className="selectModalWrap">
          <button className="selectBtn del">삭제</button>
          <button className="selectBtn">수정</button>
          <button className="selectBtn">취소</button>
        </div>
      </div>
    </div>
  );
};

export default SelectModal;
