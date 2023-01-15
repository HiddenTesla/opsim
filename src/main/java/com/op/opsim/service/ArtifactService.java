package com.op.opsim.service;

import com.op.opsim.generated.Artifact;
import com.op.opsim.generated.ArtifactType;
import com.op.opsim.generated.Stat;
import com.op.opsim.generated.StatType;
import com.op.opsim.model.arrgegator.MainStatScalingAggregator;
import com.op.opsim.model.common.Lottery;
import com.op.opsim.model.arrgegator.MainStatProbabilityAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Random;

@Component
public class ArtifactService {

    final private Random random = new Random();

    @Autowired
    private MainStatProbabilityAggregator mainStatProbabilityAggregator;

    @Autowired
    private MainStatScalingAggregator mainStatScalingAggregator;

    final private Lottery<String, Double> statLottery = new Lottery<>();

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
        artifact.setLevel(0);

        assignMainStat(artifact);

        return artifact;
    }

    private ArtifactType generateRandomArtifactType() {
        ArtifactType[] types = ArtifactType.values();
        int index = random.nextInt(types.length);
        return types[index];
    }

    private void assignMainStat(Artifact artifact) {
        int rarity = artifact.getRarity();
        Map<String, Double> s = mainStatProbabilityAggregator.byRarityAndType(rarity, artifact.getType());
        String mainStatTypeStr = statLottery.makeLottery(s, null);
        StatType mainStatType = StatType.fromValue(mainStatTypeStr);
        List<Double> scalar = mainStatScalingAggregator.byRarityAndType(rarity, mainStatType);

        Stat mainStat = new Stat();
        mainStat.setType(mainStatType);
        mainStat.setValue(scalar.get(0));
        artifact.setMainStat(mainStat);
    }
}
