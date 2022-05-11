import axios from "axios";
import React, { useState } from "react";

import UpdateModal from "./UpdateModal";
import "./SelectModal.scss";

const SelectModal = (props) => {
  const { selectModalHandler, data, readModalHandler, setDelRerender } = props;
  const [updateModal, setUpdateModal] = useState(false);

  const boardDelete = async (delData) => {
    try {
      const response = await axios.delete("/deleteBoard", {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("jwtToken"),
        },
        data: delData,
      });
      return response;
    } catch (error) {
      console.log("error", error);
      return null;
    }
  };

  const delBtnHandler = async () => {
    const res = await boardDelete(data);
    if (res !== null) {
      selectModalHandler();
      readModalHandler();
      setDelRerender(true);
    }
  };
  const updateModalHandler = () => {
    if (updateModal === false) {
      setUpdateModal(true);
    } else {
      setUpdateModal(false);
    }
  };

  return (
    <div className="modal1">
      <div className="modalOverlay1" onClick={selectModalHandler}></div>
      <div className="modalContent1">
        <div className="selectModalWrap">
          <button className="selectBtn del" onClick={delBtnHandler}>
            삭제
          </button>
          {updateModal && (
            <UpdateModal
              data={data}
              setDelRerender={setDelRerender}
              selectModalHandler={selectModalHandler}
              updateModalHandler={updateModalHandler}
            />
          )}
          <button className="selectBtn" onClick={updateModalHandler}>
            수정
          </button>
          <button className="selectBtn" onClick={selectModalHandler}>
            취소
          </button>
        </div>
      </div>
    </div>
  );
};

export default SelectModal;
