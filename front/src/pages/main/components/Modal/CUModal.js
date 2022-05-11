import React, { useState, useEffect, useRef } from "react";
import axios from "axios";
import "./CUModal.scss";
import profileImg from "../../../../profileImg/1.jpg";

const CUModal = (props) => {
  const { cancelModalHandler } = props;
  const [files, setFiles] = useState("");
  const titleRef = useRef();
  const contentRef = useRef();

  const onLoadFile = (event) => {
    const file = event.target.files;
    console.log(file);
    setFiles(file);
  };

  const delFile = () => {
    setFiles("");
    document.querySelector(".uploadImgBox").style.backgroundImage = "none";
  };

  const cancelModalDelFileHandler = () => {
    delFile();
    cancelModalHandler();
  };

  const preview = () => {
    if (!files) return false;

    const imgEl = document.querySelector(".uploadImgBox");
    const reader = new FileReader();

      reader.onload = () =>
        (imgEl.style.backgroundImage = `url(${reader.result})`);
      reader.readAsDataURL(files[0]);
  };

  useEffect(() => {
    preview();
    return () => preview();
  });

  const boardCreatePost = async (data) => {
    try {
      const response = await axios.post("/createBoard", data, {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("jwtToken"),
          "Content-Type": `multipart/form-data`,
        },
      });

      return response;
    } catch (error) {
      console.log("error", error);
      return null;
    }
  };

  const boardCreate = async (event) => {
    event.preventDefault();

    const formData = new FormData();

    formData.append("multipartFiles", files[0]);
    const boardInfo = {
      b_title: titleRef.current.value,
      b_content: contentRef.current.value,
    };
    formData.append("boardInfo", JSON.stringify(boardInfo)); // 직렬화해서 객체 저장

    const res = await boardCreatePost(formData);
    if (res !== null) {
      delFile();
      cancelModalHandler();
    }
  };

  return (
    <div className="modal2">
      <div className="modalOverlay2" onClick={cancelModalDelFileHandler}></div>
      <div className="modalContent2">
        <form onSubmit={boardCreate} className="modalForm">
          <div className="modalHeader">
            <button className="delBtn" onClick={cancelModalDelFileHandler}>
              취소
            </button>
            <div className="title">정보수정</div>
            <button className="completeBtn">완료</button>
          </div>

          <div className="CUWrap">
            <div className="imgWrap">
              <div className="btnWrap">
                <label htmlFor="image" className="uploadBtn">
                  <input
                    multiple
                    type="file"
                    id="image"
                    accept="img/*"
                    onChange={onLoadFile}
                  />
                </label>
                <button
                  type="button"
                  className="delBtn"
                  onClick={delFile}
                ></button>
              </div>
              <div className="uploadImg">
                <div className="uploadImgBox"></div>
              </div>
            </div>
            <div className="contentWrap">
              <div className="mainContent">
                <div className="contentProfile">
                  <img src={profileImg} alt="" />
                  <div className="myId">뚱이</div>
                </div>
                <div className="titleWrap">
                  <label htmlFor="title">
                    <h4>제목 :</h4>
                    <input ref={titleRef} type="text" id="title" />
                  </label>
                </div>
                <div className="contentText">
                  <textarea ref={contentRef}></textarea>
                </div>
                <div className="textAdd">
                  <div className="emoji"></div>
                  <div className="textCnt">93/2,200</div>
                </div>
              </div>
              <div className="localAdd">
                <span>위치 추가</span>
                <svg
                  aria-label="위치 추가"
                  className="_8-yf5 "
                  color="#8e8e8e"
                  fill="#8e8e8e"
                  height="16"
                  role="img"
                  viewBox="0 0 24 24"
                  width="16"
                >
                  <path d="M12.053 8.105a1.604 1.604 0 101.604 1.604 1.604 1.604 0 00-1.604-1.604zm0-7.105a8.684 8.684 0 00-8.708 8.66c0 5.699 6.14 11.495 8.108 13.123a.939.939 0 001.2 0c1.969-1.628 8.109-7.424 8.109-13.123A8.684 8.684 0 0012.053 1zm0 19.662C9.29 18.198 5.345 13.645 5.345 9.66a6.709 6.709 0 0113.417 0c0 3.985-3.944 8.538-6.709 11.002z"></path>
                </svg>
              </div>
              <div className="access">
                <span>접근성</span>
                <svg
                  aria-label="아래쪽 V자형 아이콘"
                  className="_8-yf5 "
                  color="#262626"
                  fill="#262626"
                  height="16"
                  role="img"
                  viewBox="0 0 24 24"
                  width="16"
                >
                  <path d="M21 17.502a.997.997 0 01-.707-.293L12 8.913l-8.293 8.296a1 1 0 11-1.414-1.414l9-9.004a1.03 1.03 0 011.414 0l9 9.004A1 1 0 0121 17.502z"></path>
                </svg>
              </div>
              <div className="setting">
                <span>고급 설정</span>
                <svg
                  aria-label="아래쪽 V자형 아이콘"
                  className="_8-yf5 "
                  color="#262626"
                  fill="#262626"
                  height="16"
                  role="img"
                  viewBox="0 0 24 24"
                  width="16"
                >
                  <path d="M21 17.502a.997.997 0 01-.707-.293L12 8.913l-8.293 8.296a1 1 0 11-1.414-1.414l9-9.004a1.03 1.03 0 011.414 0l9 9.004A1 1 0 0121 17.502z"></path>
                </svg>
              </div>
            </div>
          </div>
        </form>
      </div>
    </div>
  );
};

export default CUModal;
