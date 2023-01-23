import {Artifact, ArtifactType, Stat, StatType} from "./Artifact";

class ArtifactRenderer {

  static render(artifact: Artifact) : string {
    let SPLITTER = ", \n";
    let ret = "";
    ret += this.renderArtifactType(artifact.type);
    ret += ("+" + artifact.level.toFixed());
    ret += SPLITTER;
    ret += this.renderStat(artifact.mainStat);

    artifact.subStats.forEach((subStat) =>{
      ret += SPLITTER;
      ret += this.renderStat(subStat);
    });

    return ret;
  }

  static renderArtifactType(artifactType: ArtifactType) : string {
    // XXX: TypeScript enum switch does not work
    if (artifactType == ArtifactType.FLOWER)
      return "生之花";
    if (artifactType == ArtifactType.PLUME)
      return "死之羽";
    if (artifactType == ArtifactType.SANDS)
      return "时之沙";
    if (artifactType ==  ArtifactType.GOBLET)
      return "空之杯";
    if (artifactType == ArtifactType.CIRCLET)
      return "理之冠";
    return "未知遗物类型";
  }

  static renderStat(stat: Stat) : string {
    let ret = ""
    let statType = stat.type;
    ret += this.renderStatType(statType);
    while (ret.length < 8)
      ret += " ";

    if (statType.endsWith("FIXED") || statType == StatType.ELEMENTAL_MASTERY) {
      ret += stat.value.toFixed();
    }
    else {
      ret += stat.value.toFixed(1);
      ret += "%";
    }

    return ret;
  }

  static renderStatType(statType: StatType) : string {
    if (statType == StatType.HP_PERCENTAGE || statType == StatType.HP_FIXED)
      return "生命值";
    if (statType == StatType.DEF_PERCENTAGE || statType == StatType.DEF_FIXED)
      return "防御力";
    if (statType == StatType.ATK_PERCENTAGE || statType == StatType.ATK_FIXED)
      return "攻击力";
    if (statType == StatType.ELEMENTAL_MASTERY)
      return "元素精通";
    if (statType == StatType.ENERGY_RECHARGE)
      return "元素充能效率";
    if (statType == StatType.PHYSICAL_DAMAGE_BONUS)
      return "物理伤害加成";
    if (statType == StatType.ANEMO_DAMAGE_BONUS)
      return "风元素伤害加成";
    if (statType == StatType.GEO_DAMAGE_BONUS)
      return "岩元素伤害加成";
    if (statType == StatType.ELECTRO_DAMAGE_BONUS)
      return "雷元素伤害加成";
    if (statType == StatType.DENDRO_DAMAGE_BONUS)
      return "草元素伤害加成";
    if (statType == StatType.HYDRO_DAMAGE_BONUS)
      return "水元素伤害加成";
    if (statType == StatType.PYRO_DAMAGE_BONUS)
      return "火元素伤害加成";
    if (statType == StatType.CRYO_DAMAGE_BONUS)
      return "冰元素伤害加成";
    if (statType == StatType.CRIT_RATE)
      return "暴击率";
    if (statType == StatType.CRIT_DAMAGE)
      return "暴击伤害";
    if (statType == StatType.HEALING_BONUS)
      return "治疗加成";

    return "未知属性：" + statType;
  }

}

export {ArtifactRenderer};
