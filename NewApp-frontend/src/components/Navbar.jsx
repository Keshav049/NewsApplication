import React, { useContext, useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { AppContext } from '../context';
import { v4 as uuidv4 } from "uuid";
// import RegisterUser from '../pages/RegisterUser'
//body background color
document.body.style.backgroundColor = "lightyellow";

export default function Navbar({ children }) {

    const { coutryArray, catagoryData, countryCode,loginName } = useContext(AppContext);

    const [countryGroup, setCountryGroup] = coutryArray;
    const [code, setCode] = countryCode;
    const [catagory, setCatagory] = catagoryData;
    const [name,setName] = loginName;


    const catogories = ['business', 'entertainment', 'general', 'health', 'science', 'sports', 'technology'];


    const [catogory, setCatogory] = useState([])
    const [country, setCountry] = useState('India');

    const useEffect = (() => {
        const catogoriesSetter = () => {
            setCatogory(catogories);
        }
        catogoriesSetter()
    }, [])

    const [selectedCountry, setSelectedCountry] = useState();


    const searchSelectedCountry = countryGroup.find((obj) => {

        if (obj.altSpellings[0] === selectedCountry) {
            return true;
        }
        return false;
    });


    return (
        <React.Fragment>

            <nav className="navbar navbar-expand-lg text-white bg-dark" style={{ height: "80px", color: "white" }}>
                <div className="container-fluid">
                    <a className="navbar-brand text-white" href="/">NewsMonkey</a>
                    <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <div className="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul className="navbar-nav me-auto mb-2 mb-lg-0">


                            <div className="grid justify-center mt-14 mx-10 space-y-5">
                                <div>
                                    <select
                                        value={selectedCountry}
                                        onChange={(e) => {
                                            setSelectedCountry(e.target.value.substring(0, 2));
                                            setCode(e.target.value.substring(0, 2));
                                            setCountry(e.target.value.substring(3, e.target.value.length))
                                        }}
                                        className=" w-96 h-14 text-xl rounded-lg md:text-2xl"
                                        style={{ width: "150px", marginTop: '10px', marginLeft: '30px', marginRight: '30px' }} >
                                        <option>--Select Country--</option>
                                        {countryGroup.map((item) => {
                                            return (

                                                <option key={uuidv4()} value={[item.altSpellings[0], item.name.common]}>
                                                    {item.name.common}
                                                </option>
                                            )
                                        })}
                                    </select>
                                </div>
                            </div>
                            <div className='container-sm text-dark ' style={{ borderRadius: '5px', height: '25px', fontWeight: 'bold', marginTop: '10px', marginRight: '10px', width: '80', backgroundColor: '#98B4D4' }}>
                                <p>{country}</p>
                            </div>
                            {/* <button style={{ width: "100px",height:'30px',padding:'5px',marginTop: '10px',marginLeft:'30px',marginRight:'30px'}} type="button" className="btn btn-outline-primary text-white">{country}</button> */}
                            <li className="nav-item dropdown">
                                <a className="nav-link text-white dropdown-toggle" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    Catagories
                                </a>

                                <ul className="dropdown-menu" aria-labelledby="navbarDropdown">

                                    <li><Link to={catogories[0]} className="bg-light text-dark dropdown-item" style={{ cursor: "pointer" }} onClick={() => { setCatagory(catogories[0]) }} >{catogories[0]}</Link></li>
                                    <li><Link to={catogories[1]} className="bg-light text-dark dropdown-item" style={{ cursor: "pointer" }} onClick={() => { setCatagory(catogories[1]) }} >{catogories[1]}</Link></li>
                                    <li><Link to={catogories[2]} className="bg-light text-dark dropdown-item" style={{ cursor: "pointer" }} onClick={() => { setCatagory(catogories[2]) }}>{catogories[2]}</Link></li>
                                    <li><Link to={catogories[3]} className="bg-light text-dark dropdown-item" style={{ cursor: "pointer" }} onClick={() => { setCatagory(catogories[3]) }}>{catogories[3]}</Link></li>
                                    <li><Link to={catogories[4]} className="bg-light text-dark dropdown-item" style={{ cursor: "pointer" }} onClick={() => { setCatagory(catogories[4]) }}>{catogories[4]}</Link></li>
                                    <li><Link to={catogories[5]} className="bg-light text-dark dropdown-item" style={{ cursor: "pointer" }} onClick={() => { setCatagory(catogories[5]) }}>{catogories[5]}</Link></li>

                                </ul>

                            </li>

                            <li className="nav-item">
                                <a className="nav-link active text-white" aria-current="/" href="#">Home</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link text-white" href="/about">About</a>
                            </li>
                           <div className='contaner'>
                            <h3>{name}</h3>
                           </div>
                            <div className="container text-white" style={{ marginLeft: '100%' }}>
     
                                <Link to='/login' style={{ cursor: "pointer",borderRadius:"30%", color: 'white', fontSize: '20px' }}>
                                    <button type="button" className="btn btn-info">
                                        Login
                                    </button></Link>
                            </div>

                        </ul>

                    </div>
                </div>
            </nav>
        </React.Fragment>
    )
}
