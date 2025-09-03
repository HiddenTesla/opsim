package com.op.opsim.service;

import com.op.opsim.generated.ByElementType;
import com.op.opsim.generated.DomainCharacter;
import com.op.opsim.generated.ElementType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DomainCharacterService {

    public List<ByElementType> classifyByElementType(List<DomainCharacter> characters) {
        ElementType[] elementTypes = ElementType.values();
        List<ByElementType> byElementType = new ArrayList<>(elementTypes.length);
        for (ElementType e: elementTypes) {
            ByElementType byThisElement = new ByElementType();
            byThisElement.setElementType(e);

            characters.stream().filter(
                character -> character.getElementType() == e
            )
            .forEach(character -> {
                byThisElement.getCharacters().add(character);
            });
            byThisElement.setCount(byThisElement.getCharacters().size());
            byElementType.add(byThisElement);
        }

        return byElementType;
    }

}
