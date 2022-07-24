import React, {useEffect, useState} from 'react';
import { useHistory } from "react-router-dom";
import LoginForm from "../components/LoginForm";

//displays login page
function LoginPage(){

    localStorage.setItem("loggedin", false)

    //creates history variable for navigation
    const history = useHistory();

    //sends login data to back end
    function loginUserHandler(user){
        return fetch("http://localhost:9000/user/login", {
            method: "POST",
            body: JSON.stringify(user),
            headers: {
                "Content-Type": "application/json"
            }
        })
        .then(function(response) {
            return response.json();
        })
        .then(function(data) {

            //get data from back end
            var loginResponse = data;

            //if login data was correct, navigate to workspaces
            if(loginResponse.status === "successful login"){
                localStorage.setItem("uid", loginResponse.result)
                localStorage.setItem("loggedin", true)
                history.replace("/WorkSpace/" + loginResponse.result);
            }
            //if login failed, alert user
            else{
                alert(loginResponse.status);
            }
            return loginResponse.result;
        })
    }

    return (
        <div>
            <h1>Login Page</h1>
            <LoginForm loginUser={loginUserHandler}/>
        </div>
    )
}

export default LoginPage;