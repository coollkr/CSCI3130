
import React from 'react';
import { useHistory } from 'react-router-dom';
import CreateTaskForm from './CreateTaskForm';


function CreateTask() {
    const history = useHistory();
    const BoardID = localStorage.getItem("boardID");
    function createTaskHandler(task) {
        fetch(`http://localhost:9000/task/saveTask/${BoardID}`, {
            method: 'POST',
            body: JSON.stringify(task),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(() => history.replace(`/task/${BoardID}`));
    }
    
    if(localStorage.getItem("loggedin") === "true"){
        return (
            <CreateTaskForm createTask={createTaskHandler} />
        );
    }
    else{
        alert("Cannot access this page without logging in!")
        history.replace("/login");
    }
}

export default CreateTask;