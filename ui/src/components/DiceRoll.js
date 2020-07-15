import React, {useEffect, useState} from "react";
import axios from 'axios';
import { useAuth } from "../context/auth";
import Dice from "./Dice";

import styled from "styled-components";
import {useGameState} from "../context/GameState";

const DiceBlock = styled.div`
    display: flex;
`;

function DiceRoll(props) {
    const { authTokens } = useAuth();
    const gameId = props.gameId;
    const [ numberOfDice ] = useState(props.numberOfDice);
    const { gameState, setGameState } = useGameState()

    const rollerId = props.rollerId;
    const retryTimeout = 1000;
    let diceRefs = []
    const fetchRoll = async () => {
        const method = (authTokens.user.userId === rollerId) ? "post" : "get"
        await axios("/api/game/roll", {
            method: method,
            headers: {
                'X-Requested-With': 'XMLHttpRequest',
                'Authorization': 'Bearer ' + authTokens.token
            },
            params: {
                gameId: gameId
            },
            data: {
                gameId: parseInt(gameId),
                numberOfDice: numberOfDice
            }
        }).then(result => {
            if (result.data) {
                for (let i = 0; i < numberOfDice; i++) {
                    diceRefs[i].setResult(result.data.roll[i])
                }
                setGameState(result.data.state)
            }
        }).catch(() => {
            setTimeout(fetchRoll, retryTimeout)
        })
    };

    let dice = []
    for (let i = 0; i < props.numberOfDice; i++) {
        dice.push(
            <Dice
                key={i}
                ref={die => (diceRefs[i] = die)}
            />
        )
    }

    return <DiceBlock onClick={fetchRoll}>{dice}</DiceBlock>
}

export default DiceRoll;
