

import React from "react";
import Navigation from "./components/Navigation"
import RegisterPage from "./pages/Register"
import LoginPage from "./pages/Login"
// import HomePage from "./pages/Home"
import PasswordPage from "./pages/ForgotPassword"
import { Route, Switch } from "react-router-dom";

import Boards from './pages/Boards';
import CreateBoard from './pages/CreateBoard';
import Navigation1 from './components/Navigation1';
import WorkSpace from './pages/Workspace';
import CreateWorkSpace from './pages/CreateWorkspace';
import Navigation2 from './components/Navigation2';
import DeleteBoard from './pages/DeleteBoard';
import AddingFunction from "./pages/AddingFunction";
//import { ToastContainer } from 'react-toastify';
import Navigation3 from "./Task/Navigation3";
//import 'react-toastify/dist/ReactToastify.css';
import Task from "./Task/Task";
import CreateTask from "./Task/Createtask";
import Navigation4 from "./Task/Navigation4";
import UpdateTask from "./Task/Updatetask";
import AssignTask from "./Task/Assignuser";
import Search from "./Task/Search";
import Filter from "./Task/Filter";
import Navigation5 from "./Task/Navigation5";
import Navigation6 from "./Task/Navigation6";

function App() {

    return (
      <div>
        <Switch>
          <Route path="/register" exact>
            <Navigation />
            <RegisterPage />
          </Route>
  
          <Route path={["/", "/login"]} exact>
            <Navigation />
            <LoginPage />
  
          </Route>
  
          <Route path={['/WorkSpace/:uid']}>
              <Navigation2 />
              <WorkSpace />
          </Route>
  
          <Route path='/create-WorkSpace' exact>
              <Navigation2 />
              <CreateWorkSpace />
            </Route>
  
            <Route path={'/boards/:id'}>
              <Navigation1 />
              <Boards />
            </Route>
  
            <Route path='/create-board' exact>
              <Navigation1 />
              <CreateBoard />
            </Route>
  
            <Route path='/addmembertoworkspace' exact>
              <Navigation1 />
              <AddingFunction/>
            </Route>
  
            <Route path='/delete-board' exact>
              <Navigation1 />
              <DeleteBoard />
            </Route>
  
          <Route path="/forgotpassword" exact>
            <Navigation />
            <PasswordPage />
  
          </Route>
          
          <Route path="/task/:id" exact>
            <Navigation3 />
            <Task/>
  
          </Route>
  
          <Route path="/create-task" exact>
            <Navigation4 />
            <CreateTask/>
  
          </Route>
  
          <Route path="/update-task" exact>
            <Navigation4 />
            <UpdateTask/>
  
          </Route>
  
  
          <Route path="/assign-task" exact>
            <Navigation4 />
            <AssignTask/>
  
          </Route>
  
          <Route path="/search/:id/:result" exact>
            <Navigation5 />
            <Search/>
  
          </Route>
  
          <Route path="/filter/:id/:filter" exact>
            <Navigation6 />
            <Filter/>
  
          </Route>
  
        </Switch>
      </div>
  
    );
  
}

export default App;
