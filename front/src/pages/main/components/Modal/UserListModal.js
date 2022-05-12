import React, { useCallback, useEffect, useState } from "react";
import axios from "axios";
import "./UserListModal.scss";

const UserListModal = (props) => {
    const {UserListModalHandler} = props;
  const [userList, setUserList] = useState([]);
  const UserList = useCallback(async () => {
    try {
      const response = await axios.get("/selectAll", {
        headers: {
          Authorization:
            "Bearer " +
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjb3PthqDtgbAiLCJtX3NlcSI6IjQ3IiwiZXhwIjoxNjUyOTIyMjI0LCJ1c2VybmFtZSI6InRlc3QifQ.XsVcv4y_iTjsp9KmFBi8mxhgdKdZEp8SDXMC6I3gbSDiwE5ovPdo-MQFFn62WTXdGF7Ukqh_7vLfwGaoZK2YFg",
        },
      });
      setUserList(response.data);
    } catch (error) {
      console.log(error);
    }
  }, []);

  const deleteUser = useCallback(async (user) => {
    const userData = {
      m_seq: user,
    };
    try {
      const response = await axios.delete("/deleteMember", {
        headers: {
          Authorization:
            "Bearer " +
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjb3PthqDtgbAiLCJtX3NlcSI6IjQ3IiwiZXhwIjoxNjUyOTI4MTg0LCJ1c2VybmFtZSI6InRlc3QifQ.VxDZPpjYRCMZEyql8k9_RoYWdd5w2_oOG_l5wbgBwpCGxyrfte49KWP5DENRbAuwJgV6Jf8uaC1IkJsKzO_U7g",
        },
        data : userData
      });
      console.log(response);
    } catch (error) {
      console.log(error);
    }
    UserList();
  }, [UserList]);

  useEffect(() => {
    UserList();
  }, [UserList, deleteUser]);

  return (
    <div className="userListModal">
      <div className="userModalOverlay"></div>
      <div className="closeBtn" onClick={UserListModalHandler}>
        <svg
          aria-label="닫기"
          className="_8-yf5 "
          color="#ffffff"
          fill="#ffffff"
          height="24"
          role="img"
          viewBox="0 0 24 24"
          width="24"
        >
          <polyline
            fill="none"
            points="20.643 3.357 12 12 3.353 20.647"
            stroke="currentColor"
            strokeLinecap="round"
            strokeLinejoin="round"
            strokeWidth="3"
          ></polyline>
          <line
            fill="none"
            stroke="currentColor"
            strokeLinecap="round"
            strokeLinejoin="round"
            strokeWidth="3"
            x1="20.649"
            x2="3.354"
            y1="20.649"
            y2="3.354"
          ></line>
        </svg>
      </div>
      <div className="userModalContent">
        <div className="userListTitle">사용자 정보</div>
        <div className="userListContent">
          <table className="userListTable">
            <thead className="userListHead">
              <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Tell</th>
                <th>Email</th>
                <th>Sign-up Date</th>
                <th>Delete</th>
              </tr>
            </thead>
            <tbody>
              {userList !== undefined &&
                userList.map((data) => (
                  <tr key={data.m_seq} className="userListInfo">
                    <td>{data.m_id}</td>
                    <td>{data.m_name}</td>
                    <td>{data.m_tell}</td>
                    <td>{data.m_email}</td>
                    <td>{data.m_date}</td>
                    <td
                      className="userDelBtn"
                      onClick={() => {
                        deleteUser(data.m_seq);
                      }}
                    >
                      삭제
                    </td>
                  </tr>
                ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

export default UserListModal;
