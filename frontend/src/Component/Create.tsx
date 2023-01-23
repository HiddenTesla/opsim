import React, {Component} from 'react';
import {Input, Button} from "antd";
import {Request} from "../Request";
import {Artifact} from "../Model/Artifact";
import {ArtifactRenderer} from "../Model/ArtifactRenderer";

type Props = {
}
type States = {
  artifact: Artifact,
}

class Create extends Component<Props, States> {

  constructor(props: Props) {
    super(props);
    this.state = {
      artifact: new Artifact(),
    }
  }


  render() {
    return (
      <div>
        <Input></Input>
        <Button onClick={this.onButtonClick.bind(this)}>
          建一个新的遗物
        </Button>
      <div>{ArtifactRenderer.render(this.state.artifact)}</div>
      </div>
    )
  }


  async onButtonClick() {
    let resp = await Request.createArtifact()
    console.log(resp);
    this.setState({
      artifact: resp,
    })
  }

}

export default Create;
