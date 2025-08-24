package com.op.opsim.database.mysql.dao;

import com.op.opsim.database.mysql.mapper.DomainCharacterMapper;
import com.op.opsim.generated.DomainCharacter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DomainCharacterDao {

    @Autowired
    private DomainCharacterMapper domainCharacterMapper;

    public int insert(DomainCharacter entity) {
        int count = domainCharacterMapper.insert(entity);
        return count;
    }

}
