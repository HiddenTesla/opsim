package com.op.opsim.service;

import com.op.opsim.database.mysql.dao.CharacterDao;
import com.op.opsim.generated.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Array;
import java.util.*;

@Component
public class CharacterService {
    final private Random random = new Random();

    @Autowired
    private CharacterDao characterDao;

    public List<Character> generateRandomUpPool() {
        List<Character> upCharacters = new ArrayList<>();
        List<Character> fiveStars = characterDao.getByRarity(5);
        List<Character> fourStars = characterDao.getByRarity(4);

        int index_5 = random.nextInt(fiveStars.size());
        upCharacters.add(fiveStars.get(index_5));

        Collections.shuffle(fourStars);
        upCharacters.add(fourStars.get(0));
        upCharacters.add(fourStars.get(1));
        upCharacters.add(fourStars.get(2));
        return upCharacters;
    }

    public List<Character> generatePermanentFiveStarsPool() {
        List<Character> permanentCharacters = new ArrayList<>();
        permanentCharacters.add(characterDao.get("Qiqi"));
        permanentCharacters.add(characterDao.get("Keqing"));
        permanentCharacters.add(characterDao.get("Mona"));
        permanentCharacters.add(characterDao.get("Jean"));
        permanentCharacters.add(characterDao.get("Diluc"));
        return permanentCharacters;
    }
}
