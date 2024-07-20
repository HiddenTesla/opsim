import React from "react";
import {Button, Input} from "antd";
import {useState} from "react";

export function DurabilityCalculator() {

  // Default 来源于90级宵宫
  const [basicHp, setBasicHp] = useState(10164);
  const [extraHp, setExtraHp] = useState(4780);
  const [basicDef, setBasicDef] = useState(615);
  const [extraDef, setExtraDef] = useState(0);
  const [enemyLevel, setEnemyLevel] = useState(90);


  function doCalculate() {
    let hp = Number(basicHp) + Number(extraHp);
    let def = Number(basicDef) + Number(extraDef);
    let eLevel = Number(enemyLevel);

    // 私下里称其为承伤系数，取决于敌人等级和我方防御力，公式为 (敌人等级 * 5 + 100)/(我方防御力 + 敌人等级 * 5 + 100)。
    // 控制变量，在该公式里未涉及到的因素不变的情况下，我方角色受到的伤害正比于承伤系数。

    let numerator = eLevel * 5 + 100;
    let damageCoefficient = numerator / (def + numerator);

    let durability = hp / damageCoefficient;

    console.log(durability);

  }

  return(<>

    <Input
      placeholder={"基础生命值"}
      onChange={e => setBasicHp(e.target.value)}
    />
    <Input
      placeholder={"附加生命值"}
      onChange={e => setExtraHp(e.target.value)}
    />
    <Input
      placeholder={"基础防御力"}
      onChange={e => setBasicDef(e.target.value)}
    />
    <Input
      placeholder={"附加防御力"}
      onChange={e => setExtraDef(e.target.value)}
    />
    <Input
      placeholder={"敌人等级"}
      onChange={e => setEnemyLevel(e.target.value)}
    />
    <Button
      onClick={doCalculate}
    >
      计算
    </Button>

  </>);
}
