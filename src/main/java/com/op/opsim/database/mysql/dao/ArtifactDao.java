package com.op.opsim.database.mysql.dao;

import com.op.opsim.database.mysql.mapper.ArtifactMapper;
import com.op.opsim.generated.Artifact;
import com.op.opsim.generated.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ArtifactDao {

    @Autowired
    private ArtifactMapper artifactMapper;

    public void insert(Artifact entity) {
        artifactMapper.insertMain(entity);
        int artifactId = entity.getArtifactId();

        List<Stat> subStats = entity.getSubStats();
        if (subStats != null)
          artifactMapper.insertSubStat(artifactId, subStats);
    }
}
