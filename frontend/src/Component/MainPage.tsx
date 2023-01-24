import React from "react";
import Create from "./Create";
import Get from "./Get"
import Enhance from "./Enhance";

const MainPage = (prop: any) => {

  return (
    <div>
      <Get/>
      <Create/>
      <Enhance/>
    </div>
  )

}

export default MainPage;
