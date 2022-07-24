import React, { useRef } from 'react';
import { Button, TextField, Typography } from '@mui/material';
function AssignTaskForm(props) {
    const emailRef = useRef();
    function assignTask(e) {
        e.preventDefault();
        const email = emailRef.current.value;
        props.AssignTask(email);
    }

    return (
        <section style={{ marginTop: '32px' }}>
            <Typography variant='h2' component='h2'>Assign Task</Typography>
            <form onSubmit={assignTask}>
                <TextField
                    id='Email'
                    placeholder='email'
                    variant='outlined'
                    required
                    fullWidth
                    margin='dense'
                    inputRef={emailRef} />
                
                <Button type='submit' variant='contained' color='primary' sx={{ marginTop: '16px' }}>
                    Confirm
                </Button>
            </form>
        </section>
    );
}

export default AssignTaskForm;