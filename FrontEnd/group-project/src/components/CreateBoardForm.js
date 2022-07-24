import { Button, TextField, Typography } from '@mui/material';
import React, { useRef } from 'react';

function CreateBoardForm(props) {
    const boardNameRef = useRef();
    const boardDespRef = useRef();

    function createBoard(e) {
        e.preventDefault();
        const boardName = boardNameRef.current.value;
        const boardDesp = boardDespRef.current.value;

        const board = {
            board_name: boardName,
            board_description: boardDesp
        };

        props.createBoard(board);
    };

    return (
        <section style={{ marginTop: '32px' }}>
            <Typography variant='h2' component='h2'>Create New Board</Typography>
            <form onSubmit={createBoard}>
                <TextField
                    id='boardName'
                    placeholder='Board Name'
                    variant='outlined'
                    required
                    fullWidth
                    margin='dense'
                    inputRef={boardNameRef} />
                <TextField
                    id='boardDesp'
                    placeholder='Board Description'
                    variant='outlined'
                    multiline
                    rows={4}
                    required
                    fullWidth
                    margin='dense'
                    inputRef={boardDespRef} />
                <Button type='submit' variant='contained' color='primary' sx={{ marginTop: '16px' }}>
                    Create Board
                </Button>
            </form>
        </section>
    );
};

export default CreateBoardForm;