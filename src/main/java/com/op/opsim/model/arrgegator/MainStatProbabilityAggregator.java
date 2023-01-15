package com.op.opsim.model.arrgegator;

import com.op.opsim.generated.ArtifactType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "opsim.artifact.probability")
public class MainStatProbabilityAggregator {
    private Map<String, Map<String, Map<String, Double> > >  mainstat;

    public Map<String, Map<String, Map<String, Double>>> getMainstat() {
        return mainstat;
    }

    public MainStatProbabilityAggregator setMainstat(Map<String, Map<String, Map<String, Double>>> mainstat) {
        this.mainstat = mainstat;
        return this;
    }

    public Map<String, Map<String, Double> > byRarity(Integer rarity) {
        Map<String, Map<String, Double> > any = mainstat.get("any");
        if (any != null)
            return any;
        return mainstat.get(rarity.toString());
    }

    public Map<String, Double> byRarityAndType(Integer rarity, ArtifactType type) {
        Map<String, Map<String, Double> > _byRarity = byRarity(rarity);
        Map<String, Double> _byRarityAndType = _byRarity.get(type.value().toLowerCase());
        return _byRarityAndType;
    }

}
