import React, {Component} from 'react';
import {Input, Button} from "antd";
import {Request} from "../Request";

type Props = {}
type States = {}

class Create extends Component<Props, States> {


  render() {
    return (
      <div>
        <Input></Input>
        <Button onClick={this.onButtonClick.bind(this)}>
          建一个新的遗物
        </Button>
      </div>
    )
  }


  async onButtonClick() {
    let resp = await Request.createArtifact()
    console.log(resp);

  }

}

export default Create;
