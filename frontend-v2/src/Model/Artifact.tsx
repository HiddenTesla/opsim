enum ArtifactType {
    UNDEFINED,
    FLOWER,
    PLUME,
    SANDS,
    GOBLET,
    CIRCLET
}

enum StatType {
    UNDEFINED,
    HP_PERCENTAGE,
    HP_FXIED,
    ATK_PERCENTAGE,
    ATK_FIXED,
    DEF_PERCENTAGE, 
    DEF_FIXED,
    ELEMENTAL_MASTERY,
    ENERGY_RECHARGE,
    PHYSICAL_DAMAGE_BONUS,
    ANEMO_DAMAGE_BONUS,
    GEO_DAMAGE_BONUS,
    ELECTRO_DAMAGE_BONUS,
    DENDRO_DAMAGE_BONUS,
    HYDRO_DAMAGE_BONUS, 
    PYRO_DAMAGE_BONUS,
    CRYO_DAMAGE_BONUS,
    CRIT_RATE,
    CRIT_DAMAGE,
    HEALING_BONUS
}

type artifactType = keyof typeof ArtifactType
type statType = keyof typeof StatType;

class Artifact {
    artifactId: number;
    type: ArtifactType;
    level: number;
    mainStat: Stat;
    subStats: Stat[];

    constructor(){
        this.artifactId = 0;
        this.type = ArtifactType.UNDEFINED;
        this.level = 0;
        this.mainStat = new Stat();
        this.subStats = []
    }
}

class Stat {
    type: StatType;
    value: number;

    constructor() {
        this.type = StatType.UNDEFINED;
        this.value = 0;
    }
}

export {
    ArtifactType,
    Artifact,
    StatType,
    Stat
}
