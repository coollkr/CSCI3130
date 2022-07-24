import React from 'react';
import { Grid, Card, CardContent, Typography, Button, Stack } from '@mui/material';

import { Link } from 'react-router-dom';
function ViewTask(props) {

    

    return (
        <section style={{ marginTop: '32px' }}>
            
            <Grid container spacing={2}>
                
            {props.task.map((task) => {
                    return (
                        <Grid item xs={12} sm={12} md={4} lg={4} key={task.id}>
                            <Card elevation={6}>
                                <CardContent>
                                    <Typography component='h5' variant='h5'>
                                    ID: {task.id}
                                    </Typography>
                                    <Typography component='h4' variant='h4'>
                                    Name: {task.name}
                                    </Typography>
                                    <Typography component='p' variant='p'>
                                    {task.date}
                                    </Typography>
                                    <Typography component='p' variant='p'>
                                    Assigned to: {task.userFullName}
                                    </Typography>
                                    <Typography component='p' variant='p'>
                                    Status: {task.status}
                                    </Typography>
                                    <Link to={'/update-task'}>
                                        <Button variant='contained' sx={{ marginTop: '100px' }} onClick={() => localStorage.setItem("taskID", task.id)}>
                                            Update Status
                                        </Button>
                                    </Link>
                                    <Link to={'/assign-task'}>
                                        <Button variant='contained' sx={{ marginTop: '100px' }} onClick={() => localStorage.setItem("taskID", task.id)}>
                                            Assign user
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

export default ViewTask;