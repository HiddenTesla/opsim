package com.op.opsim.database.mysql.dao;

import com.op.opsim.database.mysql.mapper.CharacterMapper;
import com.op.opsim.generated.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CharacterDao {
    @Autowired
    private CharacterMapper characterMapper;

    public Character get(int characterId) {
        return characterMapper.findCharacterById(characterId);
    }

    public Character get(String characterName) {
        return characterMapper.findCharacterByName(characterName);
    }

    public List<Character> getByRarity(int rarity) {
        return characterMapper.findCharacterByRarity(rarity);
    }

    public List<Character> getAll() {
        return characterMapper.findAllCharacters();
    }
}
