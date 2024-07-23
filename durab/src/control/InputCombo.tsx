import TextArea from "antd/es/input/TextArea";
import {Col, Input, Row, Typography} from "antd";
import {Rollup} from "vite";

type Props = {
  note: string;
  defaultValue: string;
  onChange: any;
}

export const InputCombo: React.FC<Props> = ({note, defaultValue, onChange, }) => {


  return(<>
    <Row gutter={16}>
      <Col span={8}>
        {note}
      </Col>
      <Col span={8}>
        <Input
          defaultValue={defaultValue}
          onChange={onChange}
        />
      </Col>
    </Row>
  </>);
}
