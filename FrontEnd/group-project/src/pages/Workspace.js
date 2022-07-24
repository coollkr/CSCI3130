import React, { useEffect, useState } from 'react';
import { useParams, useHistory } from 'react-router-dom';
import ViewSpace from '../components/ViewWorkSpace';



function WorkSpace() {
    const history = useHistory();
    const {uid} = useParams();
    const [spacesData, setSpaceData] = useState([]);
    const url1 = `http://localhost:9000/workspace/getAllWorkspaces/${uid}`

    useEffect(function () {
        fetch(url1,{method:'GET'})
            .then(response => response.json())
            .then(spaces => {
                setSpaceData(spaces);
            });
    }, [url1]);

    if(localStorage.getItem("loggedin") === "true"){
        return (
            <section>
                <ViewSpace space={spacesData} />
            </section>
        );
    }
    else{
        alert("Cannot access this page without logging in!")
        history.replace("/login");
    }
    
};

export default WorkSpace;