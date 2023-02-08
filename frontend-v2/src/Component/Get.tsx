
import React from "react";
import reactLogo from '../assets/react.svg'

type Props = {}
type State = {}

class Get extends React.Component<Props, State> {
    constructor(props: Props) {
        super(props);
        this.state = {

        }
    }

    render() {
        return (
            <div>
                <a href="https://vitejs.dev" target="_blank">
                <img src="/vite.svg" className="logo" alt="Vite logo" />
                </a>
                <a href="https://reactjs.org" target="_blank">
                <img src={reactLogo} className="logo react" alt="React logo" />
                </a>
            </div>
        )
    }

}

export default Get;