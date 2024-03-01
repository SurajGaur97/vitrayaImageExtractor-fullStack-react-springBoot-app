import axios from 'axios'
import React, { useState, useEffect } from "react";
import { useNavigate } from 'react-router-dom';
import { API_BASE_URL, API_ENDPOINTS } from '../util/ApiConfig';


const LoginComponent = () => {
    const [credentials, setCredentials] = useState({
        userName: '',
        password: ''
    });

    useEffect(() => {
        checkIfSessionIsActive();
    }, []); // The empty dependency array ensures that the effect runs only once after the initial render

    //Used for nevigating the page through routes on a string.
    const navigate = useNavigate();

    const handleInputChange = (event) => {
        setCredentials({
            ...credentials,
            [event.target.name]: event.target.value
        });
    }

    const loginUser = () => {
        //formating the object structure in 'username' and 'password'
        const { userName, password } = credentials;

        //assigning the value in another constant loginCred
        const loginCred = { userName, password };

        //Api for login user
        const loginUser = `${API_BASE_URL}${API_ENDPOINTS.loginUser}`

        const headers = {
            'Content-Type': 'application/json',
            'Accept': '*/*',
        };
        //Post method calling
        axios.post(loginUser, loginCred, { headers: headers, withCredentials: true } ).then(response => {

            if(response.data.status === 200) {
                //After login redirecting it to welcome page
                navigate('/home');
            } else if(response.data.status !== 403) {
                alert("User name or Password is Incorrect!")
            } else {
                alert("Something went wrong!")
            }
        }).catch(error => {
            console.error(error);
            alert("There is some Error!");
        });
    }

    const checkIfSessionIsActive = () => {
        //Api for checking active session
        const checkApi = `${API_BASE_URL}${API_ENDPOINTS.isSessionActive}`

        const headers = {
            'Content-Type': 'application/json',
            'Accept': '*/*',
        };
        //Post method calling
        axios.get(checkApi, { headers: headers, withCredentials: true } ).then(response => {
            //redirecting to home page if response data is true
            if(response.data) {
                navigate('/home');
            }        
        }).catch(error => {
            console.error(error);
        });
    }


    return (
        <div className="App">
            <header className="App-header">
                <h1 className="text-center">Login</h1>
                <div>
                    <input className="text-box" type="text" placeholder="User Name" name="userName" onChange={handleInputChange} /><br />
                    <br />
                    <input className="text-box" type="password" placeholder="Password"  name="password" onChange={handleInputChange} /><br />
                    <br />
                    <button className="button-green" onClick={loginUser}>Login</button>
                </div>
            </header>
        </div>
    );
}

export default LoginComponent;
