import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import Project from './Project';
import Login from './Login';
import Home from './Home';
import * as serviceWorker from './serviceWorker';
import {BrowserRouter as Router, Route, Link} from 'react-router-dom';
import ProjectSummaryItem from './ProjectSummaryItem';
import UserSummaryItem from './UserSummaryItem';
ReactDOM.render(<Router>
    <div>
    <Route exact path="/" component={App}/>
    <Route path="/Project" component={Project} />
    <Route path="/Login" component={Login} />
    <Route path="/Home" component={Home} />
    <Route path="/ProjectItem" component={ProjectSummaryItem} />
    <Route path="/UserItem" component={UserSummaryItem}/>
    </div>
    </Router>
    , document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
