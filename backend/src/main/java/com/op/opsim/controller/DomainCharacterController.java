package com.op.opsim.controller;

import com.op.opsim.database.mysql.dao.DomainCharacterDao;
import com.op.opsim.generated.ByElementType;
import com.op.opsim.generated.DomainCharacter;
import com.op.opsim.generated.ElementType;
import com.op.opsim.generated.StatCharacterResponse;
import com.op.opsim.generated.WeaponType;
import com.op.opsim.service.DomainCharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/domain/character")
public class DomainCharacterController {

    @Autowired
    private DomainCharacterDao domainCharacterDao;

    @Autowired
    private DomainCharacterService domainCharacterService;

    @PostMapping
    public ResponseEntity<Object> addCharacter(
        @RequestBody DomainCharacter character,
        HttpServletRequest placeholder
    ) {
        int inserted = domainCharacterDao.insert(character);
        return inserted > 0?
            ResponseEntity.ok(character) :
            ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<Object> getAll() {
        List<DomainCharacter> characters = domainCharacterDao.getAll();
        return ResponseEntity.ok(characters);
    }


    @GetMapping(path = "/by-element")
    public ResponseEntity<Object> getAllByElement() {
        List<DomainCharacter> characters = domainCharacterDao.getAll();
        List<ByElementType> byElementTypes = domainCharacterService.classifyByElementType(characters);

        ElementType[] elementTypes = ElementType.values();
        WeaponType[]  weaponTypes  = WeaponType.values();

        StatCharacterResponse rr = new StatCharacterResponse();
        rr.getElementTypes().addAll(Arrays.asList(elementTypes));
        rr.getWeaponTypes().addAll(Arrays.asList(weaponTypes));
        rr.getByElementTypes().addAll(byElementTypes);

        return ResponseEntity.ok(rr);
    }
}
