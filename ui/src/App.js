import React, { useState } from 'react';
import { BrowserRouter as Router, Link, Route } from "react-router-dom"
import GameList from './pages/GameList';
import Login from './pages/Login';
import Signup from './pages/Signup';
import Confirm from './pages/Confirm'
import { AuthContext } from "./context/auth";
import PrivateRoute from "./PrivateRoute";

function App(props) {
    let existingTokens = localStorage.getItem("tokens");
    try {
        existingTokens = JSON.parse(existingTokens);
    } catch (e) {
        existingTokens = {};
    }
    const [authTokens, setAuthTokens] = useState(existingTokens);

    const setTokens = (data) => {
        localStorage.setItem("tokens", JSON.stringify(data));
        setAuthTokens(data);
    }

    return (
        <AuthContext.Provider value={{ authTokens, setAuthTokens: setTokens  }}>
            <Router>
                <div>
                    <PrivateRoute exact path="/" component={GameList}/>
                    <PrivateRoute exact path="/games" component={GameList}/>
                    <Route path="/login" component={Login} />
                    <Route path="/signup" component={Signup} />
                    <Route path="/confirm/:token" component={Confirm} />
                </div>
            </Router>
        </AuthContext.Provider>
    );
}
export default App;