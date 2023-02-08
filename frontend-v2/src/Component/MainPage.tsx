import React from "react";
import Get from "./Get";

type Props = {}
type State = {
    count: number
}

class MainPage extends React.Component<Props, State> {
    constructor(props: Props) {
        super(props);
        this.state = {
            count: 0
        }
    }

    render() {
        return (
            <div className="App">
            <h1>OPSIM</h1>
            <Get />
            </div>
        );
    }
}

export default MainPage;
