import React from 'react';
import { Grid, Card, CardContent, Typography, Button } from '@mui/material';

import { Link } from 'react-router-dom';
function ViewBoards(props) {

    

    return (
        <section style={{ marginTop: '32px' }}>
            <Typography variant='h2' component='h2'>Boards</Typography>
            <Grid container spacing={2}>
                {props.boards.map((board) => {
                    return (
                        <Grid item xs={12} sm={12} md={4} lg={4} key={board.id}>
                            <Card elevation={6}>
                                <CardContent>
                                    <Typography component='h5' variant='h5'>
                                    ID: {board.id}
                                    </Typography>
                                    <Typography component='h4' variant='h4'>
                                    Name: {board.board_name}
                                    </Typography>
                                    <Typography component='p' variant='p'>
                                    {board.board_description}
                                    </Typography>
                                    <Link to={'/task/' + board.id}>
                                        <Button variant='contained' sx={{ marginTop: '100px' }} onClick={() => localStorage.setItem("boardID", board.id)}>
                                            View All Tasks
                                        </Button>
                                    </Link>
                                </CardContent>
                            </Card>
                        </Grid>
                    );
                })}
            </Grid>
        </section>
    );
};

export default ViewBoards;