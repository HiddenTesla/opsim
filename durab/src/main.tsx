import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.tsx'
import './index.css'
import {DurabilityCalculator} from "./control/DurabilityCalculator.tsx";

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <DurabilityCalculator/>
  </React.StrictMode>,
)
