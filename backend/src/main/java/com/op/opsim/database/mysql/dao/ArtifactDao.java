package com.op.opsim.database.mysql.dao;

import com.op.opsim.database.mysql.mapper.ArtifactMapper;
import com.op.opsim.generated.Artifact;
import com.op.opsim.generated.EnhanceResult;
import com.op.opsim.generated.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
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

    @Transactional
    public void recordEnhance(Artifact artifact, Stat newSubStat) {
        updateMainStat(artifact);
        insertSingleSubStat(artifact.getArtifactId(), newSubStat);
    }

    @Transactional
    public void updateMainStat(Artifact artifact) {
        artifactMapper.updateMainStat(artifact);
    }

    @Transactional
    public void insertSingleSubStat(int artifactId, Stat subStat) {
        artifactMapper.insertSubStat(artifactId, Arrays.asList(subStat));
    }

    public Artifact get(int artifactId) {
        Artifact artifact = artifactMapper.findMain(artifactId);
        if (artifact == null)
            return null;

        List<Stat> storedSubStats = artifactMapper.findSubStats(artifactId);
        List<Stat> returnedSubStats = artifact.getSubStats();
        returnedSubStats.clear();
        if (storedSubStats != null)
            returnedSubStats.addAll(storedSubStats);

        return artifact;
    }

    @Transactional
    public EnhanceResult rewind(Artifact artifact) {
        updateMainStat(artifact);
        int artifactId = artifact.getArtifactId();
        int newestSubStatId = artifactMapper.findNewestSubStat(artifactId);
        artifactMapper.copySubStat(newestSubStatId);
        artifactMapper.deleteSubStat(newestSubStatId);

        Artifact rewoundArtifact = this.get(artifactId);
        Stat rewoundSubStat = artifactMapper.findRewindSubStat(newestSubStatId);

        EnhanceResult rewindResult = new EnhanceResult();
        rewindResult.setArtifact(rewoundArtifact);
        rewindResult.setEnhancedSubStat(rewoundSubStat);

        return rewindResult;
    }
}
