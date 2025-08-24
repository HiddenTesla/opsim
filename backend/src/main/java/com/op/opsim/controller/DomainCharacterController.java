package com.op.opsim.controller;

import com.op.opsim.database.mysql.dao.DomainCharacterDao;
import com.op.opsim.generated.DomainCharacter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/domain/character")
public class DomainCharacterController {

    @Autowired
    private DomainCharacterDao domainCharacterDao;


    @PostMapping
    public ResponseEntity<Object> addCharacter(
        @RequestBody DomainCharacter character,
        HttpServletRequest placeholder
    ) {
        int inserted = domainCharacterDao.insert(character);
        return inserted > 0?
            ResponseEntity.ok(character) :
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

}
