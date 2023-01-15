package com.op.opsim.controller;

import com.op.opsim.generated.Artifact;
import com.op.opsim.service.ArtifactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/artifact")
public class ArtifactController {

    @Autowired
    private ArtifactService artifactService;

    final static private int RARITY = 5;


    @PostMapping(path = {"", "/"})
    public ResponseEntity<Object> createArtifact() {
        Artifact artifact = artifactService.createRandomTypeArtifact(RARITY);
        return new ResponseEntity<>(artifact, HttpStatus.OK);
    }

}
