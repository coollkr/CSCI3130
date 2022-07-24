import React, { useRef } from 'react';
import { Box, AppBar, Toolbar, Typography, useControlled } from '@mui/material';
import { Link, useHistory } from 'react-router-dom';

function Navigation3() {
    const history = useHistory();

    const uid = localStorage.getItem("uid");
    const workspaceID = localStorage.getItem("active_workspace");
    const BoardID = localStorage.getItem("boardID");

    const SearchRef = useRef();
    const FilterRef = useRef();

    function updateSearch(e) {
        e.preventDefault();
        const result = SearchRef.current.value;
        history.replace('/search/' + BoardID + "/" + result);
    }

    function filterTask(e) {
        e.preventDefault();
        const filter = FilterRef.current.value;
        history.replace('/filter/' + BoardID + "/" + filter);
    }

    return (
        <Box sx={{ flexGrow: 1 }}>
            <AppBar position="static">
                <Toolbar>
                    <Link to={'/boards/' + workspaceID} style={{ textDecoration: 'none', color: 'white' }}>
                        <Typography variant="h6" component="div" sx={{ padding: '0 8px' }}>
                            Go back to board
                        </Typography>

                    </Link>
                    <Link to='/create-Task' style={{ textDecoration: 'none', color: 'white' }}>
                        <Typography variant="h6" component="div" sx={{ padding: '0 8px' }}>
                            Create Task
                        </Typography>
                    </Link>
                    <form onSubmit={updateSearch}>
                        <input type="text" id="header-search" placeholder="Search tasks" name="search" ref={SearchRef}/>
                        <button type="submit">Search</button>
                    </form>
                    <form onSubmit={filterTask}>
                    <label for="cars">Filter Tasks By: </label>
                        <select name="filters" id="filters" ref={FilterRef}>
                            <option disabled selected value> -- select an option -- </option>
                            <option value="Today">Due Today</option>
                            <option value="Week">Due This Week</option>
                            <option value="Overdue">Overdue</option>
                        </select>
                        <input type="submit" value="Submit"/>
                    </form>
                </Toolbar>
            </AppBar>
        </Box>
    );
}

export default Navigation3;