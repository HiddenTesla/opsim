package com.op.opsim.model.arrgegator;

import com.op.opsim.model.common.Lottery;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "opsim.inventory.probability")
public class ItemDropAggregator {

    private Lottery<Integer, Double> itemDropRateLoggery = new Lottery<>();

    private Map<Integer, Double> dropRate;

    public Map<Integer, Double> getDropRate() {
        return dropRate;
    }

    public void setDropRate(Map<Integer, Double> dropRate) {
        this.dropRate = dropRate;
    }

    public int drop() {
        int dropCount = itemDropRateLoggery.makeLottery(dropRate, null);
        return dropCount;
    }

}

