package com.op.opsim.database.mysql.dao;

import com.op.opsim.database.mysql.mapper.GameRankingMapper;
import com.op.opsim.generated.GameRanking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameRankingDao {

    @Autowired
    private GameRankingMapper gameRankingMapper;

    public int insert(GameRanking entity) {
        int count = gameRankingMapper.insert(entity);
        return count;
    }

    public GameRanking findById(long rankId) {
        GameRanking entity = gameRankingMapper.findById(rankId);
        return entity;
    }

    public List<GameRanking> findAll() {
        List<GameRanking> entities = gameRankingMapper.findAll();
        return entities;
    }
}
