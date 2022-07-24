import React, {useRef} from 'react';
import { useHistory } from "react-router-dom";
import * as yup from 'yup'
import YupPassword from 'yup-password'
YupPassword(yup)

//creates password reset form and validates data
function PasswordForm(props){

    //sets variables for form data    
    const emailRef = useRef();
    const securityQuestionRef = useRef();
    const passwordRef = useRef();

    //handles form submission
    function submitHandler(event){
        event.preventDefault();

        //Read values from form
        const email = emailRef.current.value;
        const securityAnswer = securityQuestionRef.current.value;
        const newPw = passwordRef.current.value;

        //create user for back end
        const user = {email, securityAnswer, newPw}
        //validate form data
        validation(email, newPw, user);
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
            var result = props.resetPassword(user);
        } catch (e) {
            alert(e.errors);
            console.log(e.errors);
        }
    }

    return (
        //creates form for reset password
        <form onSubmit={submitHandler}>
            <input type="email" required placeholder="Email" ref={emailRef}/>
            <br/>
            <label>Security Question: </label>
            <input type="text" required placeholder="Favorite Color?" ref={securityQuestionRef}/>
            <br/>
            <input type="password" required placeholder="New Password" ref={passwordRef}/>
            <br/>
            <button>Submit</button>
        </form>
    );
}

export default PasswordForm;