import React, {Component} from "react";
import styled from "styled-components";

const RangeBox = styled.div`
    display: flex;
    flex-direction: column;
`
const RangeEntry = styled.div`
    display: flex;
    justify-content: space-between;
    max-width: 200px;
`
class Ranges extends Component {
    constructor(props) {
        super(props)
        this.state = {
            ranges: props.ranges
        }
    }

    updateRanges = (ranges) => {
        this.setState({
            ranges: ranges
        })
    }

    renderRange = (range) => {
        if (range.lowRange === range.highRange) {
            return (
                <RangeEntry key={range.lowRange}>
                    <div>{range.result}</div>
                    <div>{range.highRange}</div>
                </RangeEntry>
            )
        } else {
            return (
                <RangeEntry key={range.lowRange}>
                    <div>{range.result}</div>
                    <div>{range.lowRange} - {range.highRange}</div>
                </RangeEntry>
            )
        }
    }

    render() {
         return (
            <RangeBox>
                {this.state.ranges.map((range) =>
                    this.renderRange(range)
                )}
            </RangeBox>
        )
    }
}

export default Ranges;
