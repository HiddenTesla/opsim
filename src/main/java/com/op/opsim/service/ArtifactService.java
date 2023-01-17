package com.op.opsim.service;

import com.op.opsim.generated.Artifact;
import com.op.opsim.generated.ArtifactType;
import com.op.opsim.generated.Stat;
import com.op.opsim.generated.StatType;
import com.op.opsim.model.arrgegator.MainStatScalingAggregator;
import com.op.opsim.model.arrgegator.QuantitySubStatAggregator;
import com.op.opsim.model.arrgegator.SubStatProbabilityAggregator;
import com.op.opsim.model.arrgegator.SubStatScalingAggregator;
import com.op.opsim.model.common.Lottery;
import com.op.opsim.model.arrgegator.MainStatProbabilityAggregator;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

@Component
public class ArtifactService implements InitializingBean {

    final private Random random = new Random();

    private int minStatQuantity = 1;

    private int maxStatQuantity = 1;

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

    @Autowired
    private QuantitySubStatAggregator quantitySubStatAggregator;

    final private Lottery<StatType, Double> statLottery = new Lottery<>();

    final private Lottery<Integer, Double> subStatQuantityLottery = new Lottery<>();

    @Override
    public void afterPropertiesSet() {
        setMinAndMaxStatQuantity();
    }

    private void setMinAndMaxStatQuantity() {
        Map<Integer, Double> map =  quantitySubStatAggregator.getSubstat();
        Set<Integer> keySet = map.keySet();
        if (keySet.isEmpty())
            return;

        // Get an arbiary value from the map
        for (Integer k: keySet) {
            minStatQuantity = k;
            maxStatQuantity = k;
            break;
        }

        for (Integer k: keySet) {
            if (k < minStatQuantity)
                minStatQuantity = k;
            if (k > maxStatQuantity)
                maxStatQuantity = k;
        }
    }

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
        int n = subStatQuantityLottery.makeLottery(quantitySubStatAggregator.getSubstat(), null);

        List<Stat> subStats = artifact.getSubStats();
        for (int i = 0; i < n; i++) {
            Stat subStat = generateNewSubStat(artifact);
            subStats.add(subStat);
        }

    }

    private Stat generateNewSubStat(Artifact artifact) {
        int rarity = artifact.getRarity();
        List<Stat> exsitingSubStats = artifact.getSubStats();
        List<StatType> exclusion = new ArrayList<>(maxStatQuantity);
        exclusion.add(artifact.getMainStat().getType());
        for (Stat s: exsitingSubStats) {
            exclusion.add(s.getType());
        }

        Map<StatType, Double> s = subStatProbabilityAggregator.byRarityAndType(rarity, artifact.getType());
        StatType statType = statLottery.makeLottery(s, exclusion);

        double statValue = subStatScalingAggregator.byRarityAndType(rarity, statType);
        statValue *= subStatRange[random.nextInt(subStatRange.length)];

        Stat subStat = new Stat();
        subStat.setType(statType);
        subStat.setValue(statValue);

        return subStat;
    }

    public Stat enhance(Artifact artifact) {
        List<Stat> subStats = artifact.getSubStats();
        if (subStats.size() < maxStatQuantity) {
            Stat newSubStat = generateNewSubStat(artifact);
            subStats.add(newSubStat);
            return newSubStat;
        }
        else {
            int rarity = artifact.getRarity();
            Stat enhancedSubStat = subStats.get(random.nextInt(subStats.size()));
            StatType enhancedStatType = enhancedSubStat.getType();
            double enhancedStatValue = subStatScalingAggregator.byRarityAndType(rarity, enhancedStatType);
            enhancedStatValue *= subStatRange[random.nextInt(subStatRange.length)];
            enhancedSubStat.setValue(enhancedStatValue);
            return enhancedSubStat;
        }
    }
}
