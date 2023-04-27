import { Link } from "react-router-dom";

import axios from "axios";
import { useState,useContext } from "react";
import { myAxios } from "../components/common/helper";
import { AppContext } from "../context.jsx";

export default function Login() {

    // const [loginData, setLoginData] = useState({
    //     email: '',
    //     password: ''
    // })

    // const[returnLoginData,setReturnLoginData]=useState();
    // const { email, password } = loginData;



    const [returnLoginData, setReturnLoginData] = useState();
    const { coutryArray, catagoryData, countryCode,loginName } = useContext(AppContext);
    const [name,setName] = loginName;

    const [logindata, setLoginData] = useState({
        username: '',
        password: ''
    });
    const { username, password } = logindata;
   

    const handleLogin = async (event) => {
        event.preventDefault();
        try {
            console.log('Logindata ::: : ', logindata)

         await fetch("http://localhost:8080/api/v1/registration/login", {
                method: "Post",
                headers: {
                 'Content-Type': 'application/json'
                 },
                body: JSON.stringify(logindata)

            }).then((result)=>{
                result.json().then((resp)=>{
                   setName(name);
                    alert("Login");
                setReturnLoginData(resp);
                })
            })

        // myAxios.post('/login').then((response)=>{
        //     console.log(" response : ",response.json());
        //   })
    
        } catch (error) {
            console.log('error : ', error)
        }

        
    }


    console.log('returnLoginData ', returnLoginData)
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
                            <div className="col-sm-10" style={{ whiteSpace: 'break-spaces' }}>
                                <label htmlFor="inputEmail4" className="form-label ">Username</label>
                                <input type="email" className="form-control"
                                    value={username}
                                    onChange={(event) => {
                                        setLoginData({
                                            ...logindata,
                                            username: event.target.value
                                        })
                                    }}
                                    placeholder="Enter Email" id="inputEmail4" />
                            </div>
                            <div className="col-sm-10">
                                <label htmlFor="inputPassword4" className="form-label">Password</label>
                                <input type="password" className="form-control" name="password"
                                    value={password}
                                    onChange={(event) => {
                                        setLoginData({
                                            ...logindata,
                                            password: event.target.value
                                        })

                                    }} placeholder="Enter Password" id="inputPassword4" />
                            </div>
                            <div className="col-12">
                                <div className="form-check">
                                    <input className="form-check-input" type="checkbox" id="gridCheck" />
                                    <label className="form-check-label" htmlFor="gridCheck">
                                        Check me out
                                    </label>
                                </div>
                            </div>
                            <div className="col-12">
                                <Link to='/registerUser'><button type="submit" className="btn btn-light">Register Your self</button></Link>
                            </div>
                           
                            <div className="col-12">
                                <button type="submit" className="btn btn-primary" onClick={handleLogin}>Sign in</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </>
    );
}