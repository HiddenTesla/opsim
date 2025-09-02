import React, {Component} from 'react';
import {Button} from "antd";
import {OpsimRequest} from "../OpsimRequest";
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
        <Button
            type="primary"
            onClick={this.executeCreate.bind(this)}>
          建一个新的遗物
        </Button>
      <div>{ArtifactRenderer.render(this.state.artifact)}</div>
      </div>
    )
  }


  async executeCreate() {
    let resp = await OpsimRequest.createArtifact()
    console.log(resp);
    this.setState({
      artifact: resp,
    })
  }

}

export default Create;
