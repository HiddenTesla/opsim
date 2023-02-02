import React, {Component} from 'react';
import {Input, Button} from "antd";
import {Request} from "../Request";
import {Artifact} from "../Model/Artifact";
import {ArtifactRenderer} from "../Model/ArtifactRenderer";


type Props = {
}

type States = {
  inputText: string;
  artifact: Artifact;
}

class Get extends Component<Props, States> {

  constructor(props: Props) {
    super(props);
    this.state = {
      inputText: "",
      artifact: new Artifact(),
    };
  }


  render() {
    return(
      <div>
        <Input
          onPressEnter={this.executeGet.bind(this)}
          onChange={ (e) => {
          this.setState({
            inputText: e.target.value,
          })
        }}>
        </Input>

        <Button
          onClick={this.executeGet.bind(this)}
        >
          查询遗物
        </Button>

        <div>{ArtifactRenderer.render(this.state.artifact)}</div>

      </div>
    );
  }

  async executeGet() {
    let resp = await Request.getArtifact(this.state.inputText);
    this.setState({artifact: resp});
  }
}

export default Get;
