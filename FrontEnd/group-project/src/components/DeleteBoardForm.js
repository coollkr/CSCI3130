import React, { useRef } from 'react';
import { Button, TextField, Typography } from '@mui/material';
function DeleteBoardForm(props) {
    const idRef = useRef();
    function deleteBoard(e) {
        e.preventDefault();
        const id = idRef.current.value;
        props.deleteBoard(id);
    }

    return (
        <section style={{ marginTop: '32px' }}>
            <Typography variant='h2' component='h2'>Delete Board</Typography>
            <form onSubmit={deleteBoard}>
                <TextField
                    id='boardName'
                    placeholder='Board ID'
                    variant='outlined'
                    required
                    fullWidth
                    margin='dense'
                    inputRef={idRef} />
                
                <Button type='submit' variant='contained' color='primary' sx={{ marginTop: '16px' }}>
                    Delete Board
                </Button>
            </form>
        </section>
    );
}

export default DeleteBoardForm;