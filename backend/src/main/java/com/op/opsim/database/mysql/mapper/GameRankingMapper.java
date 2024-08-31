package com.op.opsim.database.mysql.mapper;

import com.op.opsim.generated.GameRanking;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

    @Select(
        "SELECT * FROM `game_ranking` WHERE `rank_id` = #{rankId} "
    )
    @Results(id = "gameRankingMapper", value = {
        @Result(property = "rankId",     column = "rank_id"),
    })
    GameRanking findById(long rankId);

    @Select(
       "SELECT * FROM `game_ranking`"
    )
    @ResultMap("gameRankingMapper")
    List<GameRanking> findAll();
}
