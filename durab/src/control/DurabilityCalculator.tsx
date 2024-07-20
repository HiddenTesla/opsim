import React from "react";
import {Button, Input, Table} from "antd";
import {useState} from "react";
import {DurabilityTableEntry} from "../model/DurabilityTableEntry.tsx";

export function DurabilityCalculator() {

  // Default 来源于90级宵宫
  const [basicHp, setBasicHp] = useState(10164);
  const [extraHp, setExtraHp] = useState(4780);
  const [basicDef, setBasicDef] = useState(615);
  const [extraDef, setExtraDef] = useState(0);
  const [enemyLevel, setEnemyLevel] = useState(90);

  const [tableDataSource, setTableDataSource] = useState([] as DurabilityTableEntry[]);

  const columns = [
    {
      dataIndex: "totalHp",
      title: "生命值",
      key: "totalHp",
    },
    {
      dataIndex: "totalDef",
      title: "防御力",
      key: "totalDef",
    },
    {
      dataIndex: "enemyLevel",
      title: "敌人等级",
      key: "enemyLevel",
    },
    {
      dataIndex: "durability",
      title: "坦度",
      key: "durability",
    },

  ];

  function doCalculate() {
    let hp = Number(basicHp) + Number(extraHp);
    let def = Number(basicDef) + Number(extraDef);
    let eLevel = Number(enemyLevel);

    // 私下里称其为承伤系数，取决于敌人等级和我方防御力，公式为 (敌人等级 * 5 + 100)/(我方防御力 + 敌人等级 * 5 + 100)。
    // 控制变量，在该公式里未涉及到的因素不变的情况下，我方角色受到的伤害正比于承伤系数。

    let numerator = eLevel * 5 + 100;
    let damageCoefficient = numerator / (def + numerator);

    let durability = hp / damageCoefficient;

    let ds = tableDataSource;
    tableDataSource.push({
      totalHp: hp,
      totalDef: def,
      enemyLevel: eLevel,
      durability: durability,
    });

    // setTableDataSource(ds);

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

    <Table
      dataSource={tableDataSource}
      columns={columns}
    />

  </>);
}
