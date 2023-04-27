import './App.css'

import React, { Component, useEffect, useState, useContext } from 'react'
import Navbar from './components/Navbar'
import News from './components/News'
import { BrowserRouter, Routes, Route, Link, useNavigation } from 'react-router-dom'

import { AppContext } from './context'
import Login1 from './pages/Login'
import RegisterUser from './pages/RegisterUser';



export default function App(props) {

  const { countryCode, catagoryData } = useContext(AppContext);
  const [code, setCode] = countryCode;
  const [catagory, setCatagory] = catagoryData;
  console.log('catagory : ', catagory)
  useEffect(() => {

    const setUrlPath = () => {

      document.getElementById('linkId').click();
    }
    setCode(code.toLowerCase())
    setCatagory(catagory.toLowerCase())
    console.log('categories : ', catagory)
    setUrlPath();
  })

  return (
    <>

      <BrowserRouter>
        <Link className='Active' to={code} id={"linkId"}></Link>

        <Navbar />
        <Routes>
          <Route path='/login' element={<Login1/>} />
          <Route path='/registerUser' element={<RegisterUser/>} />
           <Route path={catagory} element={<News country={code} webu={"https://newsapi.org/v2/top-headlines"} pageSize={8} catagories={catagory} apiKey={"0a0f58b168184719845611dd68ee279b"} />}></Route> 
          <Route path={code} element={<News country={code} webu={"https://newsapi.org/v2/top-headlines"} pageSize={8} catagories={''} apiKey={"0a0f58b168184719845611dd68ee279b"} />}></Route> 
        </Routes>
      </BrowserRouter>


    </>
  )
}

