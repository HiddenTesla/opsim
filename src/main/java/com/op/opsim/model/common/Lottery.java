package com.op.opsim.model.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Lottery<K, V extends Number> {

    private Random random = new Random();

    public K makeLottery(Map<K, V> pool, Collection<K> exclusion) {
        List<Map.Entry<K, V>> filteredPool = filterPool(pool, exclusion);
        if (filteredPool.isEmpty())
            return null;

        double blossomBound = 0.0;
        for (Map.Entry<K, V> e: filteredPool) {
            V v = e.getValue();
            blossomBound += v.doubleValue();
        }

        double blossom = random.nextDouble();
        blossom *= blossomBound;

        double accumlated = 0.0;
        for (Map.Entry<K, V> e: filteredPool) {
            K k = e.getKey();
            V v = e.getValue();
            accumlated += v.doubleValue();
            if (accumlated >= blossom)
                return k;
        }
        return filteredPool.get(filteredPool.size() -1).getKey();
    }

    private List<Map.Entry<K, V> > filterPool(Map<K, V> pool, Collection<K> exclusion) {
        List<Map.Entry<K, V> > theList = new ArrayList<>(pool.size());
        for (Map.Entry<K, V> e: pool.entrySet()) {
            K k = e.getKey();
            if (exclusion == null || !exclusion.contains(k)) {
                theList.add(e);
            }
        }
        return theList;
    }
}
