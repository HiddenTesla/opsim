package com.op.opsim.controller;

import com.op.opsim.database.mysql.dao.GameRankingDao;
import com.op.opsim.generated.GameRanking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = {"/ranking", "/rank", "/game-ranking"})
public class GameRankingController {

    @Autowired
    private GameRankingDao gameRankingDao;

    @PostMapping(path = {"", "/"})
    public ResponseEntity<Object> insert(
            @RequestBody GameRanking entity
    ) {
        gameRankingDao.insert(entity);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

}
