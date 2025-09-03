import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import MainPage from "./Component/MainPage";
import 'antd/dist/antd.css';
import {CharacterStats} from "./Component/domain/CharacterStats";

export const App = () => {

  return (
    <Router>
      <Routes>
        <Route path="/" element={<MainPage />}/>
        <Route path="/characters" element={<CharacterStats />}/>
      </Routes>
    </Router>
  );
}
