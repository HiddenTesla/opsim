package com.op.opsim.service;

import com.op.opsim.generated.Artifact;
import com.op.opsim.generated.ArtifactType;
import com.op.opsim.generated.Stat;
import com.op.opsim.generated.StatType;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ArtifactService {

    final private Random random = new Random();

    public Artifact createRandomTypeArtifact(int rarity) {
        ArtifactType artifactType = generateRandomArtifactType();
        Artifact artifact = createGivenTypeArtifact(rarity, artifactType);
        return artifact;
    }

    public Artifact createGivenTypeArtifact(int rarity, ArtifactType artifactType) {
        Artifact artifact = new Artifact();
        artifact.setArtifactId(-1);
        artifact.setRarity(rarity);
        artifact.setType(artifactType);

        Stat mainStat = generateMainStat(rarity, artifactType);
        artifact.setMainStat(mainStat);

        return artifact;
    }

    private ArtifactType generateRandomArtifactType() {
        ArtifactType[] types = ArtifactType.values();
        int index = random.nextInt(types.length);
        return types[index];
    }

    private Stat generateMainStat(int rarity, ArtifactType artifactType) {
        Stat mainStat = new Stat();
        mainStat.setType(StatType.ELEMENTAL_MASTERY);
        mainStat.setValue(28.0);
        return mainStat;
    }

    private void assignMainStat(Artifact artifact) {

    }
}
