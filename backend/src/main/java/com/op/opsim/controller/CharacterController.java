package com.op.opsim.controller;

import com.op.opsim.database.mysql.dao.CharacterDao;
import com.op.opsim.generated.Character;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/character")
public class CharacterController {
    @Autowired
    private CharacterDao characterDao;

    @GetMapping(path = {"", "/"})
    public ResponseEntity<Object> getAllCharacters() {
        return checkNullAndReturn(characterDao.getAll());
    }

    @GetMapping(path = {"", "/{id}"})
    public ResponseEntity<Object> getCharacter(@PathVariable int id) {
        return checkNullAndReturn(characterDao.get(id));
    }

    @GetMapping(path = {"", "/{name}"})
    public ResponseEntity<Object> getCharacter(@PathVariable String name) {
        return checkNullAndReturn(characterDao.get(name));
    }

    @GetMapping(path = {"", "/rarity/{rarity}"})
    public ResponseEntity<Object> getCharacterByRarity(@PathVariable int rarity) {
        List<Character> character = characterDao.getByRarity(rarity);
        return checkNullAndReturn(character);
    }

    private ResponseEntity<Object> checkNullAndReturn(Character character) {
        if (character == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(character, HttpStatus.OK);
        }
    }

    private ResponseEntity<Object> checkNullAndReturn(List<Character> characterList) {
        if (characterList == null || characterList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(characterList, HttpStatus.OK);
        }
    }
}
