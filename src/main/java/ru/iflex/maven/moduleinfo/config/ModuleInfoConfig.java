package ru.iflex.maven.moduleinfo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "module.info")
public class ModuleInfoConfig {
    private String url;
    private String method;
}
