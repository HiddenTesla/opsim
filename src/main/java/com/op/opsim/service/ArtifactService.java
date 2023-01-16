package com.op.opsim.service;

import com.op.opsim.generated.Artifact;
import com.op.opsim.generated.ArtifactType;
import com.op.opsim.generated.Stat;
import com.op.opsim.generated.StatType;
import com.op.opsim.model.arrgegator.MainStatScalingAggregator;
import com.op.opsim.model.arrgegator.SubStatProbabilityAggregator;
import com.op.opsim.model.arrgegator.SubStatScalingAggregator;
import com.op.opsim.model.common.Lottery;
import com.op.opsim.model.arrgegator.MainStatProbabilityAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Component
public class ArtifactService {

    final private Random random = new Random();

    @Value("${opsim.artifact.quantity.substat.min}")
    private int minStatQuantity;

    @Value("${opsim.artifact.quantity.substat.max}")
    private int maxStatQuantity;

    // Todo: put this into configuration. Somehow not work.
    private Double[] subStatRange = {0.7, 0.8, 0.9, 1.0};

    @Autowired
    private MainStatProbabilityAggregator mainStatProbabilityAggregator;

    @Autowired
    private MainStatScalingAggregator mainStatScalingAggregator;

    @Autowired
    private SubStatProbabilityAggregator subStatProbabilityAggregator;

    @Autowired
    private SubStatScalingAggregator subStatScalingAggregator;

    final private Lottery<StatType, Double> statLottery = new Lottery<>();

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
        assignSubStat(artifact);

        return artifact;
    }

    private ArtifactType generateRandomArtifactType() {
        ArtifactType[] types = ArtifactType.values();
        int index = random.nextInt(types.length);
        return types[index];
    }

    private void assignMainStat(Artifact artifact) {
        int rarity = artifact.getRarity();
        Map<StatType, Double> s = mainStatProbabilityAggregator.byRarityAndType(rarity, artifact.getType());
        StatType mainStatType = statLottery.makeLottery(s, null);
        List<Double> scalar = mainStatScalingAggregator.byRarityAndType(rarity, mainStatType);

        Stat mainStat = new Stat();
        mainStat.setType(mainStatType);
        mainStat.setValue(scalar.get(0));
        artifact.setMainStat(mainStat);
    }

    private void assignSubStat(Artifact artifact) {
        int rarity = artifact.getRarity();
        int n = random.nextInt(maxStatQuantity - minStatQuantity + 1) + minStatQuantity;

        List<Stat> subStats = artifact.getSubStats();
        List<StatType> exclusion = new ArrayList<>(n + 1);
        exclusion.add(artifact.getMainStat().getType());
        for (int i = 0; i < n; i++) {
            Map<StatType, Double> s = subStatProbabilityAggregator.byRarityAndType(rarity, artifact.getType());
            StatType statType = statLottery.makeLottery(s, exclusion);
            exclusion.add(statType);

            double statValue = subStatScalingAggregator.byRarityAndType(rarity, statType);
            statValue *= subStatRange[random.nextInt(subStatRange.length)];

            Stat subStat = new Stat();
            subStat.setType(statType);
            subStat.setValue(statValue);
            subStats.add(subStat);
        }

    }
}
