import React, { useRef } from 'react';
import { Button, TextField, Typography } from '@mui/material';
function AddingForm(props) {
    const emailRef = useRef();
    function deleteBoard(e) {
        e.preventDefault();
        const email = emailRef.current.value;
        props.Addingmember(email);
    }

    return (
        <section style={{ marginTop: '32px' }}>
            <Typography variant='h2' component='h2'>Adding Another User to This Workspace</Typography>
            <form onSubmit={deleteBoard}>
                <TextField
                    id='boardName'
                    placeholder='Member Email'
                    variant='outlined'
                    required
                    fullWidth
                    margin='dense'
                    inputRef={emailRef} />
                
                <Button type='submit' variant='contained' color='primary' sx={{ marginTop: '16px' }}>
                    Submit
                </Button>
            </form>
        </section>
    );
}

export default AddingForm;