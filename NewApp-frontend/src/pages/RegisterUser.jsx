import React from 'react'

import { useContext } from "react";

import { useState } from "react";

import { AppContext } from '../context';

import StateAndDistrict from '../utility/StateAndDistrict.json'

import { v4 as uuidv4 } from "uuid";

import { Link } from 'react-router-dom';

export default function RegisterUser() {

    const { coutryArray } = useContext(AppContext);
    const [countryGroup, setCountryGroup] = coutryArray;

    // "email":"wadwalekeshav2019@gmail.com",
    // "firstname":"Keshav",
    // "lastname":"Wadwale",
    // "password":"Keshav@123",
    // "phone":"9049553348",
    // "city":"Nanded",
    // "state":"Maharashtra",
    // "zip":"431602"
    const [registerData, setRegisterData] = useState({
        email:'',
        firstname: '',
        lastname: '',
        password: '',
        phone: '',
        city: '',
        state:'',
        zip: ''
    });

    const { name,email, password, phone, state, city, zip } = registerData;

    const handleRegister = async (event) => {
        event.preventDefault();
        try {
            console.log('registerData : ', registerData)
            

            fetch("http://localhost:8080/api/v1/registration/", {
                method: "POST",
                headers: { 
                "Content-Type": "application/json"
             },
                body: JSON.stringify(registerData)

            }).then((result)=>{
                result.json().then((resp)=>{
                    alert("Register Successfully!!");
                    // console.log("resposnse : ",resp.);
                })
            }).catch((e) =>{
                console.log("e : ",e);
            })

        } catch (error) {
            console.log('error : ', error)
        }
    }


    return (
        <>
            <div className="container" >
                <div className="card" style={{
                    width: '70%',
                    height: '40%',
                    marginTop: '50px',
                    border: '3px solid black',
                    marginLeft: '15%',
                    boxShadow: ' 10px black'
                }}>


                    <div className="card-body " style={{ backgroundColor: 'lightgray' }}>
                        <form className="row g-3 bg-skyblue" style={{ fontWeight: 'bold' }}>
                   
                            <div className="col-sm-6">
                                <label htmlFor="inputText" className="form-label">First Name</label>
                                <input type="text" name='name' className="form-control"
                                    value={name}
                                    onChange={(event) => {
                                        setRegisterData({
                                            ...registerData,
                                            firstname: event.target.value
                                        })
                                    }}
                                    placeholder="Enter Full Name" id="inputName" />
                            </div>
                            <div className="col-sm-6">
                                <label htmlFor="inputText" className="form-label">Last Name</label>
                                <input type="text" name='name' className="form-control"
                                    value={name}
                                    onChange={(event) => {
                                        setRegisterData({
                                            ...registerData,
                                            lastname: event.target.value
                                        })
                                    }}
                                    placeholder="Enter Full Name" id="inputName" />
                            </div>
                            <div className="col-sm-6">
                                <label htmlFor="inputEmail4" className="form-label ">Email</label>
                                <input type="email" name='email' className="form-control"
                                    value={email}
                                    onChange={(event) => {
                                        setRegisterData({
                                            ...registerData,
                                            email: event.target.value
                                        })
                                    }} placeholder="Enter Email" id="inputEmail4" />
                            </div>
                            <div className="col-sm-6">
                                <label htmlFor="inputPassword4" className="form-label">Password</label>
                                <input type="password" name='password' className="form-control"
                                    value={password}
                                    onChange={(event) => {
                                        setRegisterData({
                                            ...registerData,
                                            password: event.target.value
                                        })
                                    }}
                                    placeholder="Enter Password" id="inputPassword4" />
                            </div>
                            <div className="col-sm-6">
                                <label htmlFor="inputNumber" className="form-label">Phone</label>
                                <input type="Number" name='phone' className="form-control"
                                    value={phone}
                                    onChange={(event) => {
                                        setRegisterData({
                                            ...registerData,
                                            phone: event.target.value
                                        })
                                    }} id="inputNumber" placeholder="Enter Mobile Number" />
                            </div>
                            <div className="col-sm-6">
                                <label htmlFor="inputCity" className="form-label">City</label>
                                <select id="inputState" name='city' className="form-select"
                                    value={city}
                                    onChange={(event) => {
                                        setRegisterData({
                                            ...registerData,
                                            city: event.target.value
                                        })
                                    }}>
                                    <option>District...</option>
                                    {StateAndDistrict.map((item) => {
                                        //  debugger

                                        return (

                                            <option key={uuidv4()} value={item.name}>
                                                {item.name}

                                            </option>
                                        )
                                    })}
                                </select>
                            </div>
                            <div className="col-sm-6">
                                <label htmlFor="inputState" className="form-label">State</label>
                                <select id="inputState" name='state' className="form-select"
                                    value={state}
                                    onChange={(event) => {
                                        setRegisterData({
                                            ...registerData,
                                            state: event.target.value
                                        })
                                    }}>
                                    <option>States...</option>
                                    {StateAndDistrict.map((item) => {
                                        return (

                                            <option key={uuidv4()} value={item.state}>
                                                {item.state}
                                            </option>
                                        )
                                    })}
                                </select>
                            </div>
                            <div className="col-sm-6">
                                <label htmlFor="inputZip" className="form-label">Zip</label>
                                <input type="text" name='zip' className="form-control"
                                    value={zip}
                                    onChange={(event) => {
                                        setRegisterData({
                                            ...registerData,
                                            zip: event.target.value
                                        })
                                    }} id="inputZip" />
                            </div>
                            <div className="col-12">
                                <div className="form-check">
                                    <input className="form-check-input" type="checkbox" id="gridCheck" />
                                    <label className="form-check-label" htmlFor="gridCheck">
                                        Check me out
                                    </label>
                                </div>
                            </div>
                            <div className="col-12" >
                                < Link to='/login'><button type="submit" className="btn btn-light">Sign in</button></Link>                            </div>
                            <div className="col-12">
                                <button type="submit" onClick={handleRegister} className="btn btn-primary">Register Your self</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div >
        </>
    )
}
