import React, {useRef} from 'react';
import { useHistory } from "react-router-dom";
import * as yup from 'yup'
import YupPassword from 'yup-password'
YupPassword(yup)

//creates login form and validates data
function LoginForm(props){

    //variable for history for navigation
    const history = useHistory();

    //sets variables for form data
    const emailRef = useRef();
    const passwordRef = useRef();

    //handles form submission data
    function submitHandler(event){
        event.preventDefault();

        //read values from form
        const email = emailRef.current.value;
        const password = passwordRef.current.value;

        //set up user for back end
        const user = {email, password}
        //validate data
        validation(email, password, user);
    }

    //validates password using formik, Yup, and Yup-password (adapted from https://www.npmjs.com/package/yup-password)
    async function validation(email, password, user){
        //set up validation
        const schema = yup.object().shape({
            username: yup.string().email().required(),
            password: yup.string().password().required(),
        })

        //set up input objects from params
        const input = {
            username: email,
            password: password,
        }
        
        //perform validation, if successful, call function to send to back end
        try {
            const res = await schema.validate(input, { abortEarly: false })
            console.log(props.loginUser(user));
        } catch (e) {
            alert(e.errors);
            console.log(e.errors);
        }
    }

    //navigate to forgot password page when user clicks button
    function forgotPassword(event){
        event.preventDefault();
        history.replace("/ForgotPassword")
    }

    return (
        //creates login form
        <form onSubmit={submitHandler}>
            <input type="email" required placeholder="Email" ref={emailRef}/>
            <br/>
            <input type="password" required placeholder="Password" ref={passwordRef}/>
            <br/>
            <button onClick={forgotPassword}>Forgot Password</button>
            <button>Submit</button>
        </form>
    );
}

export default LoginForm;