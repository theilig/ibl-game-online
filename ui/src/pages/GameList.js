import React, {useEffect, useState} from "react";
import { Button } from "../components/AuthForm";
import { useAuth } from "../context/auth";
import axios from 'axios';
import {Redirect} from "react-router";
import GameListItem from "../components/GameListItem";
import styled from "styled-components";

const GameListBlock = styled.ul`
    list-style-type: none;
`;

const GameBlock = styled.li`
    border-style: double;
    max-width: 290px; 
`;

function GameList() {
    const { authTokens, setAuthTokens } = useAuth();
    const [ gameList, setGameList ] = useState({games:[]});
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
            setGameList(result.data);
        }).catch(() => {
            setLastError("Could not retrieve games");
        });
    }

    useEffect(() => {
        const tick = setInterval(() => {
            updateGames()
        }, 60000)
        return (() => clearInterval(tick))
    })

    if (lastError) {
        return (<Redirect to="/login" />);
    }
    return (
        <div>
            <GameListBlock>
                {gameList && gameList.games.map((game) => (
                    <GameBlock key={game.id}><GameListItem game={game} /></GameBlock>
                ))}
            </GameListBlock>
            <Button onClick={logOut}> Log out</Button>
        </div>
    )
}

export default GameList;
