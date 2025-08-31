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

class Enhance extends Component<Props, States> {

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
            onPressEnter={this.executeEnhance.bind(this)}
            onChange={ (e) => {
            this.setState({
              inputText: e.target.value,
            })
          }}>
          </Input>

          <Button
              type="primary"
              onClick={this.executeEnhance.bind(this)}
          >
            强化遗物
          </Button>

          <div>{ArtifactRenderer.render(this.state.artifact)}</div>
        </div>
    );
  }

  async executeEnhance() {
    let resp = await Request.enhanceArtifact(this.state.inputText);
    this.setState({artifact: resp});
  }

}

export default Enhance;
