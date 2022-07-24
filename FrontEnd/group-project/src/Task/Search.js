import React, { useEffect, useState } from 'react';
import { useParams, useHistory } from 'react-router-dom';
import ViewTask from './Viewtask';
import { Typography} from '@mui/material';
function Search(){
    const {id, result} = useParams();
    const history = useHistory();

    const url1 = `http://localhost:9000/task/searchTask/${id}?searchValue=` + result + `&status=TODO`
    const url2 = `http://localhost:9000/task/searchTask/${id}?searchValue=` + result + `&status=DOING`
    const url3 = `http://localhost:9000/task/searchTask/${id}?searchValue=` + result + `&status=DONE`

    const [taskData1, setTask1] = useState([]);
    const [taskData2, setTask2] = useState([]);
    const [taskData3, setTask3] = useState([]);

    useEffect(function () {
        fetch(url1,{method:'GET'})
        .then(response => response.json())
        .then(task => {
            setTask1(task);
        });

    },[url1]);

    useEffect(function () {
        fetch(url2,{method:'GET'})
        .then(response => response.json())
        .then(task => {
            setTask2(task);
            console.log(task)
        });

    },[url2]);

    useEffect(function () {
        fetch(url3,{method:'GET'})
        .then(response => response.json())
        .then(task => {
            setTask3(task);
        });

    },[url3]);

    if(localStorage.getItem("loggedin") === "true"){
        return(
            <section>
                <Typography variant='h2' component='h2'>Search Results for the keyword: {result}</Typography>
                <br></br>
                <div>
                    <Typography variant='h2' component='h2'>TODO</Typography>
                    <ViewTask task={taskData1}/>
                </div>    
                <div>
                    <Typography variant='h2' component='h2'>DOING</Typography>   
                    <ViewTask task={taskData2}/>
                </div>   
                <div>
                    <Typography variant='h2' component='h2'>DONE</Typography>
                    <ViewTask task={taskData3}/>
                </div>   
            </section>
            )
    }
    else{
        alert("Cannot access this page without logging in!")
        history.replace("/login");
    }

}

export default Search;