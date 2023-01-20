import {Artifact} from "./Model/Artifact";

class Request {

  static NO_PARAMETER = {};

  static endpoints: Record<string, string> = {
    "local":        process.env.OPSIM_URL || "http://localhost:8080",
  };

  static endpoint: string = this.endpoints["local"];


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

  static async createArtifact(): Promise<Artifact> {
    let fullUrl = this.concatUrl("/artifact", this.NO_PARAMETER);
    let respJson = await this.callBackend(fullUrl, {method: "POST"});
    return respJson as Artifact;
  }

}

export {Request};
