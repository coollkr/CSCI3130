
import React from 'react';
import { useHistory } from 'react-router-dom';
import CreateBoardForm from '../components/CreateBoardForm';


function CreateBoard() {
    
    const history = useHistory();

    function assign(b){
        const a = localStorage.getItem("active_workspace")

        fetch(`http://localhost:9000/workspace/assignBoard/${a}?board_id=${b}`,{
            method: 'PUT',
            
        }).then(() => history.replace(`/boards/${a}`));
    }


    function createBoardHandler(board) {
        
        fetch('http://localhost:9000/board/saveBoard', {
            method: 'POST',
            body: JSON.stringify(board),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(r => r.json()).then((r) => {
            assign(r.id);
        });
        
    }


    if(localStorage.getItem("loggedin") === "true"){
        return (
            <CreateBoardForm createBoard={createBoardHandler} />
            
        );
    }
    else{
        alert("Cannot access this page without logging in!")
        history.replace("/login");
    }
    
};

export default CreateBoard;