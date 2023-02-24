import {Artifact} from "../Model/Artifact";
import React, {Component} from "react";
import {Button, Input} from "antd";
import {ArtifactRenderer} from "../Model/ArtifactRenderer";
import {Request} from "../Request";

type Props = {
}

type States = {
  inputText: string;
  artifact: Artifact,
}

class Rewind extends Component<Props, States> {
  constructor(props: Props) {
    super(props);
    this.state = {
      inputText: "",
      artifact: new Artifact(),
    }
  }

  render() {
    return (
      <div>
        <Input
          onPressEnter={this.executeRewind.bind(this)}
          onChange={ (e) => {
            this.setState({
              inputText: e.target.value,
            })
          }}>
        </Input>

        <Button
          onClick={this.executeRewind.bind(this)}
        >
          倒带遗物
        </Button>

        <div>{ArtifactRenderer.render(this.state.artifact)}</div>
      </div>
    );
  }

  async executeRewind() {
    let resp = await Request.rewindArtifact(this.state.inputText);
    this.setState({artifact: resp});
  }

}

export default Rewind;
