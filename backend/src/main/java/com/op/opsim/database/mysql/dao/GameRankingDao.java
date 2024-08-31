package com.op.opsim.database.mysql.dao;

import com.op.opsim.database.mysql.mapper.GameRankingMapper;
import com.op.opsim.generated.GameRanking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameRankingDao {

    @Autowired
    private GameRankingMapper gameRankingMapper;

    public int insert(GameRanking entity) {
        int count = gameRankingMapper.insert(entity);
        return count;
    }
}
