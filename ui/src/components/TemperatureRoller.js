import React, {useEffect, useRef, useState} from "react";
import DiceRoll from "./DiceRoll"
import axios from "axios";
import {useGameState} from "../context/GameState";
import {useAuth} from "../context/auth";
import Ranges from "./Ranges";

function TemperatureRoller(props) {
    const [ ranges, setRanges ] = useState([])
    const [ lastError, setLastError ] = useState("")
    const { authTokens } = useAuth()
    const { gameState } = useGameState()
    const resultRef = useRef();

    useEffect(() => {
        const fetchData = async () => {
            await axios("/api/game/temperature", {
                method: "get",
                params: {
                    "gameId": props.gameId
                },
                headers: {
                    'X-Requested-With': 'XMLHttpRequest',
                    'Authorization': 'Bearer ' + authTokens.token
                }
            }).then(result => {
                resultRef.current.updateRanges(result.data)
                setRanges(result.data);
            })
        };
        fetchData()
    }, [])
    return (<div>
        <Ranges ranges={ranges} ref={resultRef} />
        <DiceRoll gameId={props.gameId} rollerId={gameState.teams.home.managerId} numberOfDice={2}/>
    </div>);
}
export default TemperatureRoller;
