package com.op.opsim.controller;

import com.op.opsim.generated.ItemUntilResult;
import com.op.opsim.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/inventory")
public class InventoryController {

    final private static int TARGET_COUNT = 46;

    @Autowired
    private InventoryService inventoryService;

    @RequestMapping(path = "/until")
    public ResponseEntity<Object> untilTarget(@PathVariable(required = false) Integer target) {
        if (target == null)
            target = TARGET_COUNT;

        ItemUntilResult itemUntilResult = inventoryService.dropUntilTarget(target);
        return new ResponseEntity<>(itemUntilResult, HttpStatus.OK);
    }


}
