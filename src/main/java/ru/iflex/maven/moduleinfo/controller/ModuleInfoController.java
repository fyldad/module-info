package ru.iflex.maven.moduleinfo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jboss.as.cli.CommandFormatException;
import org.springframework.web.bind.annotation.*;
import ru.iflex.maven.moduleinfo.client.WfClient;
import ru.iflex.maven.moduleinfo.model.FullInfo;
import ru.iflex.maven.moduleinfo.model.ModuleInfo;
import ru.iflex.maven.moduleinfo.operations.GetModuleInfoList;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/module-info")
@RequiredArgsConstructor
public class ModuleInfoController {

    private final List<FullInfo> fullInfoList;

    @GetMapping
    public List<FullInfo> getModuleInfo() {
        return fullInfoList;
    }

    @PostMapping
    public void addModuleInfo(@RequestBody FullInfo fullInfo) {
        log.info("registered new module {}", fullInfo.getName());
        fullInfoList.removeIf(info -> info.getName().equals(fullInfo.getName()));
        fullInfoList.add(fullInfo);
    }

}
