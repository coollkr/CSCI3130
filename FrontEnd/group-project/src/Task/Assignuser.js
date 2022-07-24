import React from 'react';
import { useHistory } from 'react-router-dom';
import AssignTaskForm from './AssignTaskForm';

function AssignTask() {
    const BoardID = localStorage.getItem("boardID")
    const tID = localStorage.getItem("taskID")
    const workspaceID = localStorage.getItem("active_workspace");
    const history = useHistory();
    function assignTaskHandler(email){
        fetch(`http://localhost:9000/task/addUserToTask/${tID}?email=` + email + `&workspace_id=` + workspaceID, {
            method: 'GET',
            
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(() => history.replace(`/task/${BoardID}`));
    }

    if(localStorage.getItem("loggedin") === "true"){
        return(
            <AssignTaskForm AssignTask ={assignTaskHandler}/>
        );
    }
    else{
        alert("Cannot access this page without logging in!")
        history.replace("/login");
    }
    
}

export default AssignTask;