package com.op.opsim.database.mysql.mapper;

import com.op.opsim.generated.GameRanking;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

public interface GameRankingMapper {

    @Options(keyProperty = "rankId", keyColumn = "rank_id", useGeneratedKeys = true)
    @Insert(
    "INSERT INTO game_ranking (" +
        " name, year, note, " +
        " publisher, `rank`, pros, cons  " +
        " ) VALUES (" +
        " #{name}, #{year}, #{note}, " +
        " #{publisher}, #{rank}, #{pros}, #{cons}" +
        ")"
    )
    int insert(GameRanking entity);
}
