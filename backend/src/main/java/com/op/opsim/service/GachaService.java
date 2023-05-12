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
    final private Random random = new Random();

    @Value("${opsim.gacha.weight}")
    private Integer gachaMaxWeight;

    @Autowired
    private GachaDao gachaDao;

    private Integer batchSize = 10;

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public Gacha singleWish() {
        Gacha gacha = new Gacha();
        return gacha;
    }

    public ArrayList<Gacha> batchWish() {
        Gacha gacha = new Gacha();
        ArrayList<Gacha> results = new ArrayList<>();
        results.add(gacha);
        return results;
    }
}