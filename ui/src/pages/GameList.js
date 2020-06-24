import React, {useEffect, useState} from "react";
import { Button } from "../components/AuthForm";
import { useAuth } from "../context/auth";
import axios from 'axios';
import {Redirect} from "react-router";
import GameListItem from "../components/GameListItem";
import styled from "styled-components";
import DiceRoll from "../components/DiceRoll";
import DR from "../components/DR";

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
    const [ selectedGame, setSelectedGame ] = useState( 0 )
    function logOut() {
        setAuthTokens();
        setLastError("Logged out");
    }

    useEffect(() => {
        const fetchData = async () => {
            await axios("/api/games", {
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
        };
        fetchData()
        const tick = setInterval(fetchData, 60000)
        return (() => clearInterval(tick))
    }, [authTokens.token])

    if (lastError) {
        return (<Redirect to="/login" />);
    }

    if (selectedGame > 0) {
        let location = "/game/" + selectedGame;
        return (<Redirect push to={location} />);
    }
    return (
        <div>
            <h2>Current Games</h2>
            <GameListBlock>
                {gameList && gameList.games.map((game) => (
                    <GameBlock key={game.gameId} onClick={() => {setSelectedGame(game.gameId)}}><GameListItem game={game} /></GameBlock>
                ))}
            </GameListBlock>
            <Button onClick={logOut}> Log out</Button>
            <DiceRoll gameId={1} numberOfDice={3} />
        </div>
    )
}

export default GameList;
