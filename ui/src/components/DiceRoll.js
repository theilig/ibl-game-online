import React, {useEffect, useState} from "react";
import axios from 'axios';
import { useAuth } from "../context/auth";
import Dice from "./Dice";
import times from "lodash/times";

import styled from "styled-components";

const DiceBlock = styled.div`
    display: flex;
`;

function DiceRoll(props) {
    const { authTokens } = useAuth();
    const [ roll, setRoll ] = useState();
    const [ gameId ] = useState(props.gameId);
    const [ numberOfDice ] = useState(props.numberOfDice);
    const [ error, setLastError ] = useState("");
    const retryTimeout = 1000;
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
                setRoll(result.data);
            }).catch(() => {
                setLastError("Roll failed, retrying");
                setTimeout(fetchRoll, retryTimeout)
            })
        };
        fetchRoll();
    }, [])
    if (roll) {
        return (
            times(3, i => <Dice key={i + 10} result={roll[i]} />)
        )
    } else {
        return (
            times(3, i => <Dice key={i} result="0" />)
        )
    }
}

export default DiceRoll;
