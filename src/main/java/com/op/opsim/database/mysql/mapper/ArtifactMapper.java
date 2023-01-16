package com.op.opsim.database.mysql.mapper;

import com.op.opsim.generated.Artifact;
import com.op.opsim.generated.Stat;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

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
}
