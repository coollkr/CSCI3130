import { useHistory } from "react-router-dom";
import RegisterForm from "../components/RegisterForm";

//displays register page
function RegisterPage(){

    //variable to store history for page navigation
    const history = useHistory();
    
    //send register data to back end
    function registerUserHandler(user){
        fetch("http://localhost:9000/user/save", {
            method: "POST",
            body: JSON.stringify(user),
            headers: {
                "Content-Type": "application/json"
            }
        }).then(function(response) {
            return response.json();
        })
        .then(function(data) {

            //get data from back end    
            var registerResponse = data;

            //if successful signup, move to login
            if(registerResponse.status === "successful signup"){
                history.replace("/login");
            }
            //alert user of failed registration
            else{
                alert(registerResponse.status);
            }

            return registerResponse.result;
        })
    }
    return (
        <div>
            <h1>Registration Page</h1>
            <RegisterForm registerUser={registerUserHandler}/>
        </div>
        
    )
}

export default RegisterPage;