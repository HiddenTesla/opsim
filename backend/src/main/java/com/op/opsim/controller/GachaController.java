package com.op.opsim.controller;

import com.op.opsim.database.mysql.dao.GachaDao;
import com.op.opsim.generated.Gacha;
import com.op.opsim.service.GachaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/gacha")
public class GachaController {

    @Autowired
    private GachaService gachaService;

    @Autowired
    private GachaDao gachaDao;

    @PostMapping(path = {"", "/"})
    public ResponseEntity<Object> wish() {
        try {
            Gacha gacha = gachaService.singleWish();
            //TODO: Record wish and calculate pity for single user
            return new ResponseEntity<>(gacha, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/ten")
    public ResponseEntity<Object> batchWish() {
        try {
            ArrayList<Gacha> results = gachaService.batchWish();
            return new ResponseEntity<>(results, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
