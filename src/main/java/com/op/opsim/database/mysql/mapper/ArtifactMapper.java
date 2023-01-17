package com.op.opsim.database.mysql.mapper;

import com.op.opsim.generated.Artifact;
import com.op.opsim.generated.Stat;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ArtifactMapper {

    @Insert(
            "INSERT INTO `artifact` " +
                "(`rarity`, `artifact_type`, `level`, `main_stat_type`, `main_stat_value`)" +
                " VALUES " +
                "(#{rarity}, #{type}, #{level}, #{mainStat.type}, #{mainStat.value}) "
    )
    @Options(keyProperty = "artifactId", keyColumn = "artifact_id", useGeneratedKeys = true)
    void insertMain(Artifact entity);

    @Insert(
            "<script> " +
            "INSERT INTO `sub_stat` " +
                "(`artifact_id`, `sub_stat_type`, `sub_stat_value`) " +
                "VALUES " +
                "<foreach collection='list' item='item' index='index' separator=',' > " +
                " (#{artifactId}, #{item.type}, #{item.value}) " +
                "</foreach> " +
            "</script>"
    )
    void insertSubStat(@Param("artifactId") int artifactId, @Param("list") List<Stat> stats);

    @Update(
            "UPDATE `artifact` SET " +
                " `level` = #{level}," +
                " `main_stat_value` = #{mainStat.value} " +
                " WHERE `artifact_id` = #{artifactId} "
    )
    void updateMainStat(Artifact entity);

    @Select(
            "SELECT * FROM `artifact` WHERE `artifact_id` = #{artifactId} "
    )
    @Results(id = "artifact_main", value = {
            @Result(property = "artifactId",     column = "artifact_id"),
            @Result(property = "type",   column = "artifact_type"),
            @Result(property = "mainStat.type",  column = "main_stat_type"),
            @Result(property = "mainStat.value", column = "main_stat_value"),
    })
    Artifact findMain(@Param("artifactId") int artifactId);

    @Select(
            "SELECT `artifact_id`, `sub_stat_type`, SUM(`sub_stat_value`) AS `sub_stat_value` FROM sub_stat " +
                " WHERE `artifact_id` = #{artifactId} " +
                " GROUP BY  `sub_stat_type`"
    )
    @Results(id = "sub_stat", value = {
            @Result(property = "type",  column = "sub_stat_type"),
            @Result(property = "value", column = "sub_stat_value"),
    })
    List<Stat> findSubStats(@Param("artifactId") int artifactId);
}
