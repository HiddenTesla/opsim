package com.op.opsim.database.mysql.dao;

import com.op.opsim.database.mysql.mapper.GachaMapper;
import com.op.opsim.generated.Gacha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GachaDao {
    @Autowired
    private GachaMapper gachaMapper;

    public void insert(Gacha entity) {
        gachaMapper.insertMain(entity);
        int gachaId = entity.getGachaId();
    }
}