import React from "react";
import Create from "./Create";
import Get from "./Get"
import Enhance from "./Enhance";
import Rewind from "./Rewind";

const MainPage = (prop: any) => {

  return (
    <div>
      <Get/>
      <Create/>
      <Enhance/>
      <Rewind/>
    </div>
  )

}

export default MainPage;
