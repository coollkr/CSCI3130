import { useHistory } from "react-router-dom";
import PasswordForm from "../components/PasswordForm";
import React, {useEffect, useState} from 'react';

//displays page for forgotten pasword
function PasswordPage(){

    //variable for hisotry for navigation
    const history = useHistory();

    //send password data to back end
    function resetPasswordHandler(user){
        return fetch("http://localhost:9000/user/updatePW", {
            method: "PUT",
            body: JSON.stringify(user),
            headers: {
                "Content-Type": "application/json"
            }
        })
        .then(function(response) {
            return response.json();
        })
        .then(function(data) {

            //gets data from back end
            var resetResponse = data;

            //if password reset was successful, navigate to login
            if(resetResponse.status === "successful update Password"){
                history.replace("/login");
            }
            //if password reset fails, alert user
            else{
                alert(resetResponse.status);
            }
            return resetResponse.result;
        })
    }

    return (
        <div>
            <h1>Reset your password</h1>
            <PasswordForm resetPassword = {resetPasswordHandler}/>
        </div>
        
    )
}

export default PasswordPage;