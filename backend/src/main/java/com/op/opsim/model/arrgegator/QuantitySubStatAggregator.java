package com.op.opsim.model.arrgegator;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "opsim.artifact.probability.quantity")
public class QuantitySubStatAggregator {

    private Map<Integer, Double> substat;

    public Map<Integer, Double> getSubstat() {
        return substat;
    }

    public QuantitySubStatAggregator setSubstat(Map<Integer, Double> substat) {
        this.substat = substat;
        return this;
    }

}
