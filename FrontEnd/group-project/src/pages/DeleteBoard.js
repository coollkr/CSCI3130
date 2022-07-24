import React from 'react';
import { useHistory } from 'react-router-dom';
import DeleteBoardForm from '../components/DeleteBoardForm';

function DeleteBoard() {
    const a = localStorage.getItem("active_workspace")
    const history = useHistory();
    function deleteBoardHandler(ID){
        fetch('http://localhost:9000/board/deleteBoard/'+ ID, {
            method: 'DELETE',
            body: JSON.stringify(ID),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(() => history.replace('/boards/'+a));
    }

    if(localStorage.getItem("loggedin") === "true"){
        return(
            <DeleteBoardForm deleteBoard ={deleteBoardHandler}/>
        );
    }
    else{
        alert("Cannot access this page without logging in!")
        history.replace("/login");
    }
    
}

export default DeleteBoard;