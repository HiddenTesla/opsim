package com.op.opsim.service;

import com.op.opsim.database.mysql.dao.GachaDao;
import com.op.opsim.generated.Gacha;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Random;

@Component
public class GachaService implements InitializingBean {
    private Random random = new Random();

//    @Value("${opsim.gacha.weight}")
    private Integer gachaMaxWeight;

    @Autowired
    private GachaDao gachaDao;

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public Gacha singleWish() {
        Gacha gacha = new Gacha();




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