package ru.iflex.maven.moduleinfo.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.iflex.maven.moduleinfo.config.ModuleInfoConfig;
import ru.iflex.maven.moduleinfo.model.ModuleInfo;

@Service
@Slf4j
@RequiredArgsConstructor
public class ModuleInfoClient {

    private final RestTemplate restTemplate;
    private final ModuleInfoConfig moduleInfoConfig;

    public ModuleInfo getModuleInfo(String moduleName) {
        ModuleInfo moduleInfo = null;
        String url = moduleInfoConfig.getUrl() + moduleName + moduleInfoConfig.getMethod();
        try {
            moduleInfo = restTemplate.getForObject(url, ModuleInfo.class);
            log.info("registered new module {}", moduleName);
        } catch (Exception e) {
            log.info("can not get moduleInfo for {}", moduleName);
        }
        return moduleInfo;
    }

}
