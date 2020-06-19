import React, {useEffect, useState} from "react";
import { Button } from "../components/AuthForm";
import { useAuth } from "../context/auth";
import axios from 'axios';
import {Redirect} from "react-router";

function GameList() {
    const { authTokens, setAuthTokens } = useAuth();
    const [ gameList, setGameList ] = useState([]);
    const [ lastError, setLastError ] = useState( "" );
    function logOut() {
        setAuthTokens();
        setLastError("Logged out");
    }

    const updateGames = () => {
        axios("/api/games", {
            method: "get",
            headers: {
                'X-Requested-With': 'XMLHttpRequest',
                'Authorization': 'Bearer ' + authTokens.token
            }
        }).then(result => {
            setGameList(result.games);
        }).catch(() => {
            setLastError("Could not retrieve games");
        });
    }

    useEffect(() => {
        const tick = setInterval(() => {
            updateGames()
        }, 1000)
        return (() => clearInterval(tick))
    })

    if (lastError) {
        return (<Redirect to="/login" />);
    }
    return (
        <div>
            <ul>
                {gameList && gameList.map((game) => (
                    <li key={game.id}>{game.name}</li>
                ))}
            </ul>
            <Button onClick={logOut}> Log out</Button>
        </div>
    )
}

export default GameList;
