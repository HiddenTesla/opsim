package com.op.opsim.model.arrgegator;

import com.op.opsim.generated.ArtifactType;
import com.op.opsim.generated.StatType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "opsim.artifact.probability")
public class MainStatProbabilityAggregator {
    private Map<String, Map<String, Map<StatType, Double> > >  mainstat;

    public Map<String, Map<String, Map<StatType, Double>>> getMainstat() {
        return mainstat;
    }

    public MainStatProbabilityAggregator setMainstat(Map<String, Map<String, Map<StatType, Double>>> mainstat) {
        this.mainstat = mainstat;
        return this;
    }

    public Map<String, Map<StatType, Double> > byRarity(Integer rarity) {
        Map<String, Map<StatType, Double> > any = mainstat.get("any");
        if (any != null)
            return any;
        return mainstat.get(rarity.toString());
    }

    public Map<StatType, Double> byRarityAndType(Integer rarity, ArtifactType type) {
        Map<String, Map<StatType, Double> > _byRarity = byRarity(rarity);
        Map<StatType, Double> _byRarityAndType = _byRarity.get(type.value().toLowerCase());
        return _byRarityAndType;
    }

}
