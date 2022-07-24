import { Button, TextField, Typography } from '@mui/material';
import React, { useRef } from 'react';

function CreateSpaceForm(props) {
    const spaceNameRef = useRef();
    const spaceDespRef = useRef();

    function createSpace(e) {
        e.preventDefault();
        const boardName = spaceNameRef.current.value;
        const boardDesp = spaceDespRef.current.value;

        const workspace = {
            workspace_name: boardName,
            workspace_description: boardDesp
        };

        props.createSpace(workspace);
    };

    return (
        <section style={{ marginTop: '32px' }}>
            <Typography variant='h2' component='h2'>Create New Space</Typography>
            <form onSubmit={createSpace}>
                <TextField
                    id='boardName'
                    placeholder='Space Name'
                    variant='outlined'
                    required
                    fullWidth
                    margin='dense'
                    inputRef={spaceNameRef} />
                <TextField
                    id='boardDesp'
                    placeholder='Space Description'
                    variant='outlined'
                    multiline
                    rows={4}
                    required
                    fullWidth
                    margin='dense'
                    inputRef={spaceDespRef} />
                <Button type='submit' variant='contained' color='primary' sx={{ marginTop: '16px' }}>
                    Create Workspace
                </Button>
            </form>
        </section>
    );
};

export default CreateSpaceForm;