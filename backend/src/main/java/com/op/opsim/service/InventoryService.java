package com.op.opsim.service;

import com.op.opsim.generated.ItemUntilResult;
import com.op.opsim.model.arrgegator.ItemDropAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InventoryService {

    @Autowired
    private ItemDropAggregator itemDropAggregator;

    public ItemUntilResult dropUntilTarget(int target) {
        ItemUntilResult itemUntilResult = new ItemUntilResult();
        itemUntilResult.setTarget(target);
        int nLoop = 0;
        int accu = 0;

        while (accu < target) {
            int singleDropCount = itemDropAggregator.drop();
            itemUntilResult.getSequence().add(singleDropCount);
            accu += singleDropCount;
            nLoop++;
        }

        itemUntilResult.setSize(nLoop);
        return itemUntilResult;
    }
}
