import React, {useEffect, useState} from "react";
import axios from 'axios';
import { useAuth } from "../context/auth";
import Dice from "./Dice";

import styled from "styled-components";

const DiceBlock = styled.div`
    display: flex;
`;

function DiceRoll(props) {
    const { authTokens } = useAuth();
    const [ gameId ] = useState(props.gameId);
    const [ numberOfDice ] = useState(props.numberOfDice);
    const [ setLastError ] = useState("");
    const retryTimeout = 1000;
    let diceRefs = []
    useEffect(() => {
        const fetchRoll = async () => {
            await axios("api/roll", {
                method: "post",
                headers: {
                    'X-Requested-With': 'XMLHttpRequest',
                    'Authorization': 'Bearer ' + authTokens.token
                },
                data: {
                    gameId,
                    numberOfDice
                }
            }).then(result => {
                for (let i = 0; i < numberOfDice; i++) {
                    diceRefs[i].setResult(result.data[i])
                }
            }).catch(() => {
                setLastError("Roll failed, retrying");
                setTimeout(fetchRoll, retryTimeout)
            })
        };
        fetchRoll();
    }, [authTokens.token, diceRefs, gameId, numberOfDice])

    let dice = []
    for (let i = 0; i < props.numberOfDice; i++) {
        dice.push(
            <Dice
                key={i}
                ref={die => (diceRefs[i] = die)}
            />
        )
    }

    return <DiceBlock>{dice}</DiceBlock>
}

export default DiceRoll;
