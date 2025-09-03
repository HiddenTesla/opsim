import {Artifact} from "./Model/Artifact";
import {Character, StatCharacterResponse} from "./Model/Character";
import {DomainKnowledge} from "./Model/DomainKnowledge";

class OpsimRequest {

  static NO_PARAMETER = {};

  static endpoints: Record<string, string> = {
    "local":        process.env.OPSIM_URL || "http://localhost:8080",
  };

  static endpoint: string = this.endpoints["local"];

  static HEADER_JSON = {"Content-type": "application/json"};

  static async callBackend(input: RequestInfo, init?: RequestInit): Promise<any> {
    if (!init) init = {}
    if (!init.headers) init.headers = new Headers();
    return await fetch(input, init)
      .then(
        (response) => {
          return this.generalRequestHandler(response);
        },
        (error) => {
          console.log(error); return null;
        });
  }

  static concatUrl(path: string, parameters: { [key: string]: any }): string {
    let searchParams = new URLSearchParams();
    for (let key of Object.keys(parameters))
      searchParams.set(key, parameters[key].toString());
    return this.endpoint + path + (Object.keys(parameters).length === 0 ? "" : "?" + searchParams.toString());
  }

  static generalRequestHandler(response: Response) : Promise<Object> | null {
    return response.json();
  }

  static async getArtifact(aid: string): Promise<Artifact> {
    let fullUrl = this.concatUrl("/artifact/" + aid, this.NO_PARAMETER);
    let respJson = await this.callBackend(fullUrl, {method: "GET"});
    return respJson as Artifact;
  }

  static async createArtifact(): Promise<Artifact> {
    let fullUrl = this.concatUrl("/artifact", this.NO_PARAMETER);
    let respJson = await this.callBackend(fullUrl, {method: "POST"});
    return respJson as Artifact;
  }

  static async enhanceArtifact(aid: string): Promise<Artifact> {
    let fullUrl = this.concatUrl("/artifact/" + aid + "/enhance", this.NO_PARAMETER);
    let respJson = await this.callBackend(fullUrl, {method: "POST"});
    return respJson.artifact as Artifact;
  }

  static async rewindArtifact(aid: string): Promise<Artifact> {
    let fullUrl = this.concatUrl("/artifact/" + aid + "/rewind", this.NO_PARAMETER);
    let respJson = await this.callBackend(fullUrl, {method: "POST"});
    return respJson.artifact as Artifact;
  }

  static async getAllCharacters(): Promise<Character[]> {
    let fullUrl = this.concatUrl("/domain/character/all", this.NO_PARAMETER);
    let respJson = await this.callBackend(fullUrl, {method: "GET"});
    return respJson as Character[];
  }

  static async addCharacter(character: Character): Promise<Character> {
    let fullUrl = this.concatUrl("/domain/character", this.NO_PARAMETER);
    let respJson = await this.callBackend(fullUrl, {
      method: "POST",
      body: JSON.stringify(character),
      headers: this.HEADER_JSON,
    });
    return respJson as Character;
  }

  static async getByElementTypes(): Promise<StatCharacterResponse> {
    let fullUrl = this.concatUrl("/domain/character/by-element", this.NO_PARAMETER);
    let respJson = await this.callBackend(fullUrl, {method: "GET"});
    return respJson as StatCharacterResponse;
  }

  static async getDomainKnowledge(): Promise<DomainKnowledge> {
    let fullUrl = this.concatUrl("/domain", this.NO_PARAMETER);
    let respJson = await this.callBackend(fullUrl, {method: "GET"});
    return respJson as DomainKnowledge;
  }

}

export {OpsimRequest};
