
import React from 'react';
import { useHistory } from 'react-router-dom';
import CreateSpaceForm from '../components/CreateSpaceForm';

function CreateWorkSpace() {
    // const id = useParams;
    const history = useHistory();
    const uid = localStorage.getItem("uid");
    function createSpaceHandler(workspace) {
        
        fetch(`http://localhost:9000/workspace/saveWorkspace/${uid}`, {
            method: 'POST',
            body: JSON.stringify(workspace),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(() => history.replace(`/WorkSpace/${uid}`));
    }

    if(localStorage.getItem("loggedin") === "true"){
        return (
            <CreateSpaceForm createSpace={createSpaceHandler} />
        );
    }
    else{
        alert("Cannot access this page without logging in!")
        history.replace("/login");
    }
    
};

export default CreateWorkSpace;