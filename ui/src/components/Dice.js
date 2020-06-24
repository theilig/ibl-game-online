import React, {Component} from "react";

import styled from "styled-components";

const Die = styled.div`
    width: '90px';
    height: '90px';
    fontSize: '40px';
    backgroundColor: 'rgba(108, 122, 137, 0.8)';
    color: 'white';
`;

const sides = 10;

class Dice extends Component {
    constructor(props) {
        const rollingLength = 500;
        super(props);
        this.state = {
            random: Math.floor(Math.random() * sides),
            result: props.result,
            rollingTime: rollingLength
        }
    }

    setResult = (result) => {
        this.setState({result: result})
    }

    componentDidMount() {
        const tick = setInterval(() => {
            this.setState({
                random: Math.floor(Math.random() * sides),
                rollingTime: this.state.rollingTime - 100
            })
        }, 100);
        this.setState({tick: tick})
    }

    componentWillUnmount() {
        if (this.state.tick) {
            clearInterval(this.state.tick)
        }
    }

    render() {
        if (this.state.rollingTime > 0 || this.state.result === undefined) {
            return (
                <Die>{this.state.random}</Die>
            )
        } else {
            return (
                <Die>{this.state.result}</Die>
            )
        }
    }
}

export default Dice;
