import { Button, TextField, Typography, FormControl, InputLabel, Select, MenuItem } from '@mui/material';
import React, { useRef } from 'react';

function CreateTaskForm(props) {
    const TaskNameRef = useRef();
    const TaskDateRef = useRef();
    const TaskStatusRef = useRef();

    function createTask(e) {
        e.preventDefault();
        const TaskName = TaskNameRef.current.value;
        const TaskDate = TaskDateRef.current.value;
        const TaskStatus = TaskStatusRef.current.value;

        const task = {
            name: TaskName,
            date: TaskDate,
            status: TaskStatus
        };

        props.createTask(task);
    };

    return (
        <section style={{ marginTop: '32px' }}>
            <Typography variant='h2' component='h2'>Create New Task</Typography>
            <form onSubmit={createTask}>
                <TextField
                    id='taskName'
                    placeholder='Task Name'
                    variant='outlined'
                    required
                    fullWidth
                    margin='dense'
                    inputRef={TaskNameRef} />
                <Typography variant='h4' component='h4'>Select Task Date:</Typography>
                <input type="date" id="task-date" name="task-date" ref={TaskDateRef}></input>
                <Typography variant='h4' component='h4'>Select Task Status:</Typography>
                        <select name="filters" id="filters" ref={TaskStatusRef}>
                            <option value="TODO">To Do</option>
                            <option value="DOING">Doing</option>
                            <option value="DONE">Done</option>
                        </select>
                <br></br>
                <Button type='submit' variant='contained' color='primary' sx={{ marginTop: '16px' }}>
                    Create Task
                </Button>
            </form>
        </section>
    );
};

export default CreateTaskForm;