package com.op.opsim.model.arrgegator;

import com.op.opsim.generated.StatType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "opsim.artifact.scaling")
public class SubStatScalingAggregator {

    public Map<String, Map<StatType, Double>> substat;

    public Map<String, Map<StatType, Double>> getSubstat() {
        return substat;
    }

    public SubStatScalingAggregator setSubstat(Map<String, Map<StatType, Double>> substat) {
        this.substat = substat;
        return this;
    }

    public Map<StatType, Double> byRarity(Integer rarity) {
        return substat.get(rarity.toString());
    }

    public Double byRarityAndType(Integer rarity, StatType type) {
        return byRarity(rarity).get(type);
    }
}
