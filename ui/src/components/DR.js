import React, {Component} from "react";
import Dice from "./Dice";

class DR extends Component {
    constructor(props) {
        super(props);
        this.state = {
            roll: 0
        };
        setTimeout(() => this.setState({
            roll: 5
        }), 2000);
    }

    render() {
        return (
            <Dice key={this.state.roll} result={this.state.roll} />
        );
    }
}

export default DR;
