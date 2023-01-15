package com.op.opsim.model.arrgegator;

import com.op.opsim.generated.ArtifactType;
import com.op.opsim.generated.StatType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "opsim.artifact.probability")
public class SubStatProbabilityAggregator {

    private Map<String, Map<String, Map<StatType, Double>> > substat;

    public Map<String, Map<String, Map<StatType, Double>>> getSubstat() {
        return substat;
    }

    public SubStatProbabilityAggregator setSubstat(Map<String, Map<String, Map<StatType, Double>>> substat) {
        this.substat = substat;
        return this;
    }

    public Map<String, Map<StatType, Double> > byRarity(Integer rarity) {
        Map<String, Map<StatType, Double> > any = substat.get("any");
        if (any != null)
            return any;
        return substat.get(rarity.toString());
    }

    public Map<StatType, Double> byRarityAndType(Integer rarity, ArtifactType type) {
        Map<String, Map<StatType, Double> > _byRarity = byRarity(rarity);
        Map<StatType, Double> _byRarityAndType = _byRarity.get("any");
        return _byRarityAndType;
    }
}
