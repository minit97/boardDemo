import React from 'react';
import { BrowserRouter, Route, Routes } from "react-router-dom";

import Login from './pages/login/Login';
import Join from './pages/join/Join';
import Main from './pages/main/Main';
import SelectModal from './pages/main/components/Modal/SelectModal';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route exact path="/" element={<Login />} />
        <Route path='/join' element={<Join />}/>
        <Route path='/main' element={<Main />}/>
        <Route path='/modal' element={<SelectModal />}/>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
