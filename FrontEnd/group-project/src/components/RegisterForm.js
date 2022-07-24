import React, {useRef} from 'react';
import * as yup from 'yup'
import YupPassword from 'yup-password'
YupPassword(yup)

//creates form and handles form data
function RegisterForm(props){

    //sets variables for form data
    const firstNameRef = useRef();
    const lastNameRef = useRef();
    const emailRef = useRef();
    const passwordRef = useRef();
    const securityQuestionRef = useRef();
    
    //handles form submission
    function submitHandler(event){
        event.preventDefault();

        //Read values
        const firstName = firstNameRef.current.value;
        const lastName = lastNameRef.current.value;
        const email = emailRef.current.value;
        const password = passwordRef.current.value;
        const securityAnswer = securityQuestionRef.current.value;

        //create user object for back end
        const user = {firstName, lastName, email, password, securityAnswer}
        //validate password
        validation(email, password, user)
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
            props.registerUser(user);
        } catch (e) {
            alert(e.errors);
            console.log(e.errors);
        }
    }
    
    return (
        //create form to display to user
        <form onSubmit={submitHandler}>
            <input type="text" required placeholder="First Name" ref={firstNameRef}/>
            <br/>
            <input type="text" required placeholder="Last Name" ref={lastNameRef}/>
            <br/>
            <input type="email" required placeholder="Email" ref={emailRef}/>
            <br/>
            <input type="password" required placeholder="Password" ref={passwordRef}/>
            <br/>
            <label>Security Question: </label>
            <input type="text" required placeholder="Favorite Color?" ref={securityQuestionRef}/>
            <br/>
            <button>Submit</button>
        </form>
    );
}

export default RegisterForm;