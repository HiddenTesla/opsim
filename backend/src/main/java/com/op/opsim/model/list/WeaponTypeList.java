package com.op.opsim.model.list;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Data
@ConfigurationProperties(prefix = "opsim.weapon")
public class WeaponTypeList {
    private List<Type> types;

    @Data
    public static class Type {
        private String code;
        private Map<String, String> translations;
    }
}
