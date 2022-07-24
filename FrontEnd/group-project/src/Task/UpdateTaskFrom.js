import React, { useRef } from 'react';
import { Button, TextField, Typography } from '@mui/material';
function UpdateTaskForm(props) {
    const StatusRef = useRef();
    function updateTask(e) {
        e.preventDefault();
        const status = StatusRef.current.value;
        props.UpdateTask(status);
    }

    return (
        <section style={{ marginTop: '32px' }}>
            <Typography variant='h2' component='h2'>Update Task</Typography>
            <br></br>
            <Typography variant='h4' component='h4'>Select Task Status:</Typography>
            <form onSubmit={updateTask}>
                        <select name="filters" id="filters" ref={StatusRef}>
                            <option value="TODO">To Do</option>
                            <option value="DOING">Doing</option>
                            <option value="DONE">Done</option>
                        </select>
                <br></br>
                <Button type='submit' variant='contained' color='primary' sx={{ marginTop: '16px' }}>
                    Update Task
                </Button>
            </form>
        </section>
    );
}

export default UpdateTaskForm;