import React, {useContext, useEffect, useState} from "react";
import {Button, Input, Modal, Select, Table, Tag} from "antd";
import {Character, ElementType, WeaponType} from "../../Model/Character";
import {OpsimRequest} from "../../OpsimRequest";
import {OpsimContext} from "../context/ContextProvider";

export const CharacterStats: React.FC = () => {
  const [characters, setCharacters] = useState([] as Character[]);
  const [isAddModalOpen, setIsAddModalOpen] = useState(false);
  const [characterName, setCharacterName] = useState("");
  const [characterElementType, setCharacterElementType] = useState<ElementType>("");
  const [characterWeaponType,  setCharacterWeaponType] = useState<WeaponType>("");

  const elements = ["PYRO", "HYDRO", "CRYO", "ELECTRO", "GEO", "ANEMO", "DENDRO"];
  const weapons = ["SWORD", "CLAYMORE", "POLEARM", "BOW", "CATALYST"];

  const opsimContext = useContext(OpsimContext);

  useEffect(() => {
    let elementI18ns = opsimContext.elementI18ns;
    let weaponI18ns  = opsimContext.weaponI18ns;
    console.log("Element I18n:", elementI18ns);
    console.log("Weapon I18n:", weaponI18ns);
  }, []);


  function query() {
    OpsimRequest.getAllCharacters().then(r => {
      setCharacters(r);
    });
  }

  function addCharacter() {
    console.log(`Adding character: ${characterName}, ${characterElementType}, ${characterWeaponType}`);
    let characterToAdd = {
      name: characterName,
      elementType: characterElementType,
      weaponType: characterWeaponType
    } as Character;
    OpsimRequest.addCharacter(characterToAdd).then(r => {
      query();
    });
  }

  // 构造矩阵
  const dataSource = elements.map((el, rowIndex) => {
    const row: any = { key: rowIndex, element: el };
    let rowTotal = 0;
    weapons.forEach((wp) => {
      const list = characters.filter(
        (c) => c.elementType === el && c.weaponType === wp
      );
      row[wp] = list;
      rowTotal += list.length;
    });
    row.total = rowTotal;
    return row;
  });

  // 各列总数
  const colTotals: Record<string, number> = {};
  weapons.forEach((wp) => {
    colTotals[wp] = characters.filter((c) => c.weaponType === wp).length;
  });
  const grandTotal = characters.length;

  // 表格列定义
  const columns = [
    {
      title: "元素",
      dataIndex: "element",
      key: "element",
      fixed: "left" as const,
      width: 100,
    },
    ...weapons.map((wp) => ({
      title: wp,
      dataIndex: wp,
      key: wp,
      render: (chars: { name: string }[]) =>
        chars.length > 0 ? (
          <>
            {chars.map((c) => (
              <Tag key={c.name}>{`${c.name};`}</Tag>
            ))}
          </>
        ) : (
          "-"
        ),
    })),
    {
      title: "行合计",
      dataIndex: "total",
      key: "total",
      render: (count: number) => <b>{count}</b>,
    },
  ];

  // 在末尾加一行“列合计”
  const summaryRow: any = { key: "summary", element: "列合计" };
  weapons.forEach((wp) => {
    summaryRow[wp] = [{ name: `${colTotals[wp]} 人` }];
  });
  summaryRow.total = grandTotal;

  function toOptions(arr: string[]) {
    return arr.map((item) => ({
      value: item,
      label: item,
    }));
  }


  return (<div>
    <Button
      onClick={query}
    >
      {"列出所有角色"}
    </Button>
    <Table
      bordered={true}
      columns={columns}
      dataSource={[...dataSource, summaryRow]}
      pagination={false}
      scroll={{ x: "max-content" }}
    />
    <Button
      onClick={() => setIsAddModalOpen(true)}
    >
      {"增加一名角色"}
    </Button>
    <Modal
      open={isAddModalOpen}
      onCancel={() => setIsAddModalOpen(false)}
      onOk={() => {
        addCharacter();
      }}
    >
      <div
      >
        <Input
          placeholder="角色名称"
          onChange={(e) => setCharacterName(e.target.value)}
        />
        <Select
          style={{ width: 120, marginLeft: 10 }}
          options={toOptions(elements)}
          placeholder="元素"
          onChange={(value) => setCharacterElementType(value)}
        />
        <Select
          style={{ width: 120, marginLeft: 10 }}
          options={toOptions(weapons)}
          placeholder="武器"
          onChange={(value) => setCharacterWeaponType(value)}
        />
      </div>

    </Modal>
  </div>);
};
