import React from "react";

import "./Profile.scss";
import profileImg from '../../../img/profileImg.jpg';
const Profile = () => {
  return (
    <section className="profile">
      <div className="profileImg">
        <div className="profileImgcover">
          <img src={profileImg} alt="" />
        </div>
      </div>
      <div className="profileInfo">
        <div className="profileMain">
          <div className="nameContainer">
            <h2 className="profileName">뚱이</h2>
            <div className="confirmIcon"></div>
          </div>
          <div className="buttonContainer">
            <button className="followButton">팔로우</button>
          </div>
        </div>
        <ul className="profileInfoNum">
          <li>
            게시물 <span>0</span>
          </li>
          <li>
            팔로워 <span>0</span>
          </li>
          <li>
            팔로우 <span>0</span>
          </li>
        </ul>
        <div className="profileIntro">
          <span>hello</span>
          <div>world!</div>
          <a href="#!">Hello World!</a>
        </div>
      </div>
    </section>
  );
};

export default Profile;
