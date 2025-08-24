package com.op.opsim.database.mysql.mapper;

import com.op.opsim.generated.DomainCharacter;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

public interface DomainCharacterMapper {

    @Options(keyProperty = "characterId", keyColumn = "character_id", useGeneratedKeys = true)
    @Insert({
        "INSERT INTO `domain_character` (",
        "`name`, rarity, element_type, weapon_type ",
        ") VALUES (",
        "#{name}, #{rarity}, #{elementType}, #{weaponType}",
        ")",
    })
    int insert(DomainCharacter entity);
}
