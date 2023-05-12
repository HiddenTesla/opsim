package com.op.opsim.service;

import com.op.opsim.database.mysql.dao.GachaDao;
import com.op.opsim.generated.Character;
import com.op.opsim.generated.Gacha;
import com.op.opsim.generated.GachaType;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Random;

@Component
public class GachaService implements InitializingBean {
    private Random random = new Random();

    @Value("${opsim.gacha.weight}")
    private Double gachaWeight = 0.02;

    @Autowired
    private GachaDao gachaDao;

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public Gacha singleWish() {
        Gacha gacha = new Gacha();
        gacha.setGachaId(114514);
        gacha.setRarity(5);
        gacha.setType(GachaType.CHARACTER);
        gacha.setName("qiqi");
        return gacha;
    }

    public ArrayList<Gacha> batchWish() {
        ArrayList<Gacha> results = new ArrayList<>();
        Integer batchSize = 10;
        for(int i = 0; i< batchSize; i++){
            results.add(singleWish());
        }
        return results;
    }
}