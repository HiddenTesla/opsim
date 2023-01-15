package com.op.opsim.model.arrgegator;

import com.op.opsim.generated.StatType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "opsim.artifact.scaling")
public class MainStatScalingAggregator {

    private Map<String, Map<String, List<Double> > > mainstat;

    public Map<String, Map<String, List<Double>>> getMainstat() {
        return mainstat;
    }

    public MainStatScalingAggregator setMainstat(Map<String, Map<String, List<Double>>> mainstat) {
        this.mainstat = mainstat;
        return this;
    }

    public Map<String, List<Double> > byRarity(Integer rarity) {
        return mainstat.get(rarity.toString());
    }

    public List<Double> byRarityAndType(Integer rarity, StatType type) {
        return byRarity(rarity).get(type.value());
    }
}
