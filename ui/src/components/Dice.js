import React, {useEffect, useState} from "react";

import styled from "styled-components";

const Die = styled.div`
    width: '90px';
    height: '90px';
    fontSize: '40px';
    backgroundColor: 'rgba(108, 122, 137, 0.8)';
    color: 'white';
`;

function Dice(props) {
    const sides = 10;
    const rollingLength = 500;
    const [ randomNumber, setRandom ] = useState(Math.floor(Math.random() * sides))
    const [ result ] = useState(props.result)
    const [ rolling, setRolling ] = useState(rollingLength)

    useEffect(() => {
        const tick = setInterval(() => {
            setRandom(Math.floor(Math.random() * sides));
            setRolling(rolling - 100);
        }, 100)
        return(() => clearInterval(tick))
    })
    if (rolling > 0 || result === undefined) {
        return (<Die>{randomNumber}</Die>)
    }
    return (
        <Die>{result}</Die>
    )
}

export default Dice;
