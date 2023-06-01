package com.op.opsim.database.mysql.mapper;

import com.op.opsim.generated.Character;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CharacterMapper {
    @Select(
            "SELECT * FROM `character` WHERE `character_id` = #{characterId}"
    )
    @Results(id = "character_main", value = {
            @Result(property = "characterId", column = "character_id"),
            @Result(property = "characterName", column = "character_name"),
            @Result(property = "rarity", column = "rarity"),
            @Result(property = "weaponType", column = "weapon_type"),
            @Result(property = "elementType", column = "element_type"),
    })
    Character findCharacterById(@Param("characterId") int characterId);

    @Select(
            "SELECT * FROM `character` WHERE `character_name` = #{characterName}"
    )
    @Results(id = "character_name", value = {
            @Result(property = "characterId", column = "character_id"),
            @Result(property = "characterName", column = "character_name"),
            @Result(property = "rarity", column = "rarity"),
            @Result(property = "weaponType", column = "weapon_type"),
            @Result(property = "elementType", column = "element_type"),
    })
    Character findCharacterByName(@Param("characterName") String characterName);

    @Select(
            "SELECT * FROM `character` WHERE `rarity` = ${rarity}"
    )
    @Results(id = "character_rarity", value = {
            @Result(property = "characterId", column = "character_id"),
            @Result(property = "characterName", column = "character_name"),
            @Result(property = "rarity", column = "rarity"),
            @Result(property = "weaponType", column = "weapon_type"),
            @Result(property = "elementType", column = "element_type"),
    })
    List<Character> findCharacterByRarity(@Param("rarity") int rarity);
    @Select(
            "SELECT * FROM `character`"
    )
    @Results(id = "character_all", value = {
            @Result(property = "characterId", column = "character_id"),
            @Result(property = "characterName", column = "character_name"),
            @Result(property = "rarity", column = "rarity"),
            @Result(property = "weaponType", column = "weapon_type"),
            @Result(property = "elementType", column = "element_type"),
    })
    List<Character> findAllCharacters();
}
