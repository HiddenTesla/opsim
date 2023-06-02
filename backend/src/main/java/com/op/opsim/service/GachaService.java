package com.op.opsim.service;

import com.op.opsim.database.mysql.dao.GachaDao;
import com.op.opsim.generated.*;
import com.op.opsim.generated.Character;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Component
public class GachaService implements InitializingBean {
    private Random random = new Random();

    @Value("${opsim.gacha.weight.5}")
    private Double gachaFiveStarWeight;

    @Value("${opsim.gacha.weight.4}")
    private Double gachaFourStarWeight;

    @Value("${opsim.gacha.pity}")
    private int gachaPity;

    @Autowired
    private GachaDao gachaDao;

    @Autowired
    private CharacterService characterService;

    private List<Character> pool;
    private List<Character> permanentFiveStars;

    private int accumulatedWishes = 0;

    private boolean greatPity = false;

    private boolean fourStarPityTriggered = false;
    private boolean fiveStarPityTriggered = false;

    @Override
    public void afterPropertiesSet() throws Exception {}

    private void updatePool() {
        //TODO: get current pool from db
        //TODO: add shitty weapons to pool
        this.pool = characterService.generateRandomUpPool();
        this.permanentFiveStars = characterService.generatePermanentFiveStarsPool();
    }

    public Gacha singleWish() {
        accumulatedWishes++;
        fourStarPityTriggered = accumulatedWishes % 10 == 0;
        fiveStarPityTriggered = accumulatedWishes == gachaPity;
        Gacha gacha = new Gacha();
        Character character = new Character();
        // return a 4-star every 10 gacha
        // return a 5-star every 90 gacha
        // TODO: read and write accumulated wished in db
        // TODO: separate pity for different users

        // 1. random wish to determine 4 star or 5 star
        // 2. check pity for 5 star
        int rarity = 3;
        double checker = random.nextDouble();
        if (checker <= gachaFiveStarWeight || fiveStarPityTriggered) {
            rarity = 5;
            fiveStarPityTriggered = true;
            if (greatPity) {
                character = getFiveStarFromPool(pool);
            } else {
                character = getFiveStarFromPool(permanentFiveStars);
            }
        } else if (fourStarPityTriggered || checker > gachaFiveStarWeight && checker <= gachaFiveStarWeight + gachaFourStarWeight) {
            rarity = 4;
            character = getRandomFourStarFromPool(pool);
        } else {
            character = getBasicItem();
        }

        if (fourStarPityTriggered) {
            fourStarPityTriggered = false;
        } else if (fiveStarPityTriggered) {
            accumulatedWishes = 0;
            fiveStarPityTriggered = false;
            greatPity = !greatPity;
        }

        assert character != null;
        //TODO: get auto increment id from db
        gacha.setGachaId(0);
        gacha.setRarity(character.getRarity());
        gacha.setType(GachaType.CHARACTER);
        gacha.setItemId(character.getCharacterId());
        gachaDao.insert(gacha);
        return gacha;
    }

    public ArrayList<Gacha> batchWish() {
        ArrayList<Gacha> results = new ArrayList<>();
        int batchSize = 10;
        for(int i = 0; i< batchSize; i++){
            results.add(singleWish());
        }
        return results;
    }

    private Character getFiveStarFromPool(List<Character> pool) {
        for (Character c: pool) {
            if (c.getRarity() == 5) {
                return c;
            }
        }
        return null;
    }

    private Character getRandomFourStarFromPool(List<Character> pool) {
        Collections.shuffle(pool);
        if (pool.get(0).getRarity() == 5) {
            return pool.get(1);
        } else {
            return pool.get(0);
        }
    }

    private Character getBasicItem() {
        Character basicItem = new Character();
        basicItem.setRarity(3);
        basicItem.setElement(ElementType.PYRO);
        basicItem.setWeapon(WeaponType.SWORDS);
        basicItem.setCharacterId(114514);
        basicItem.setCharacterName("Kouji");
        return basicItem;
    }
}