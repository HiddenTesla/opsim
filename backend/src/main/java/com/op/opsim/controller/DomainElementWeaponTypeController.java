package com.op.opsim.controller;

import com.op.opsim.model.list.ElementTypeList;
import com.op.opsim.model.list.WeaponTypeList;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/domain")
public class DomainElementWeaponTypeController {

    @Autowired
    private ElementTypeList elementTypeList;

    @Autowired
    private WeaponTypeList weaponTypeList;

    @RequestMapping(path = "/element-types")
    public List<? extends Object> getAllElementTypes() {
        return elementTypeList.getTypes();
    }

    @RequestMapping(path = "/weapon-types")
    public List<? extends Object> getAllWeaponTypes() {
        return weaponTypeList.getTypes();
    }

    @Data
    private static class DomainKnowledge {
        private List<? extends Object> elementTypeI18ns;
        private List<? extends Object> weaponTypeI18ns;
    }

    @RequestMapping(path = "")
    public DomainKnowledge getDomainContext() {
        DomainKnowledge response = new DomainKnowledge();
        response.elementTypeI18ns = elementTypeList.getTypes();
        response.weaponTypeI18ns = weaponTypeList.getTypes();
        return response;
    }

}
