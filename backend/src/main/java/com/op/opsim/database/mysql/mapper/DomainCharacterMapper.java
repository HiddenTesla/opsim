package com.op.opsim.database.mysql.mapper;

import com.op.opsim.generated.DomainCharacter;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

    @Select({
        "SELECT * FROM `domain_character`",
    })
    @Results({
        @Result(property = "characterId", column = "character_id"),
        @Result(property = "elementType", column = "element_type"),
        @Result(property = "weaponType",  column = "weapon_type"),
    })
    List<DomainCharacter> getAll();
}
