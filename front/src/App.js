import React from 'react';
import { BrowserRouter, Route, Routes } from "react-router-dom";

import Login from './pages/login/Login';
import Join from './pages/join/Join';
import Main from './pages/main/Main';
import SelectModal from './pages/main/components/Modal/SelectModal';
import CUModal from './pages/main/components/Modal/CUModal';
import ReadModal from './pages/main/components/Modal/ReadModal';
function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route exact path="/" element={<Login />} />
        <Route path='/join' element={<Join />}/>
        <Route path='/main' element={<Main />}/>
        <Route path='/modal1' element={<SelectModal />}/>
        <Route path='/modal2' element={<CUModal />}/>
        <Route path='/modal3' element={<ReadModal />}/>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
