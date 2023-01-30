package com.op.opsim.controller;

import com.op.opsim.database.mysql.dao.ArtifactDao;
import com.op.opsim.generated.Artifact;
import com.op.opsim.generated.EnhanceResult;
import com.op.opsim.generated.Stat;
import com.op.opsim.model.exception.ArtifactEnhanceException;
import com.op.opsim.service.ArtifactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/artifact")
public class ArtifactController {

    @Autowired
    private ArtifactService artifactService;

    @Autowired
    private ArtifactDao artifactDao;

    final static private int RARITY = 5;


    @PostMapping(path = {"", "/"})
    public ResponseEntity<Object> createArtifact() {
        Artifact artifact = artifactService.createRandomTypeArtifact(RARITY);
        artifactDao.insert(artifact);
        return new ResponseEntity<>(artifact, HttpStatus.OK);
    }


    @GetMapping(path = "/{aid}")
    public ResponseEntity<Object> getArtifact(@PathVariable int aid) {
        Artifact artifact = artifactDao.get(aid);

        if (artifact == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(artifact, HttpStatus.OK);
    }

    @RequestMapping(path = "/{aid}/enhance", method = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<Object> enhanceArtifact(@PathVariable int aid) {
        Artifact artifact = artifactDao.get(aid);
        if (artifact == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        try {
            Stat newStat = artifactService.enhance(artifact);
            artifactDao.recordEnhance(artifact, newStat);

            artifact = artifactDao.get(aid);

            EnhanceResult enhanceResult = new EnhanceResult();
            enhanceResult.setEnhancedSubStat(newStat);
            enhanceResult.setArtifact(artifact);
            return new ResponseEntity<>(enhanceResult, HttpStatus.OK);
        }
        catch (ArtifactEnhanceException e) {
           return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

}
