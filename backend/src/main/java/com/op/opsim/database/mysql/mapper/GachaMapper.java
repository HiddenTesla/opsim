package com.op.opsim.database.mysql.mapper;

import com.op.opsim.generated.Gacha;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

public interface GachaMapper {
    @Insert(
            "INSERT INTO `gacha` " +
                    "(`rarity`, `gacha_type`, `name`)" +
                    " VALUES " +
                    "(#{rarity}, #{type}, #{name}) "
    )
    @Options(keyProperty = "gachaId", keyColumn = "gacha_id", useGeneratedKeys = true)
    void insertMain(Gacha entity);
}