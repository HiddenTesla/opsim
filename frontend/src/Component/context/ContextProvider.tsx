import {createContext, useEffect, useState} from "react";
import {OpsimRequest} from "../../OpsimRequest";
import {DomainKnowledge} from "../../Model/DomainKnowledge";

export const OpsimContext = createContext<DomainKnowledge>({} );
export const ContextProvider: React.FC<{ children: React.ReactNode }> = ({children})  => {

  const [domainKnowledge, setDomainKnowledge] = useState<DomainKnowledge>({});

  useEffect(() => {
    OpsimRequest.getDomainKnowledge().then(r => {
      setDomainKnowledge(r);
    });
  }, []);

  return (
    <OpsimContext.Provider value={domainKnowledge}>
      {children}
    </OpsimContext.Provider>
  );

}
