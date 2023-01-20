
class Artifact {
  artifactId: number;

  constructor() {
    this.artifactId = 0;
  }

  static fromJson(obj: any): Artifact {
    let artifact = new Artifact();
    artifact.artifactId = obj.artifactId;

    return artifact;
  }
}

export {Artifact};
