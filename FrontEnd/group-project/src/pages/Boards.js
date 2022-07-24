import React, { useEffect, useState } from 'react';
import { useParams, useHistory } from 'react-router-dom';
import ViewBoards from '../components/ViewBoards';


function Boards() {
    const {id} = useParams();
    const history = useHistory();
    
    console.log({id})
    const [boardsData, setBoardsData] = useState([]);
    const url = `http://localhost:9000/workspace/getWorkspaceBoards/${id}`


    useEffect(function () {
        fetch(url,{method:'GET'})
        .then(response => response.json())
        .then(boards => {
            setBoardsData(boards);
        });

    },[url]);

    if(localStorage.getItem("loggedin") === "true"){
        return (
            <section>
                <ViewBoards boards={boardsData} />
            </section>
        );
    }
    else{
        alert("Cannot access this page without logging in!")
        history.replace("/login");
    }
    
};

export default Boards;