package ru.iflex.maven.moduleinfo.controller;

import lombok.RequiredArgsConstructor;
import org.jboss.as.cli.CommandFormatException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.iflex.maven.moduleinfo.client.WfClient;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ModuleInfoController {

    private final WfClient wfClient;

    @GetMapping("/module-list")
    public List<String> callModuleInfo() throws CommandFormatException, IOException {
        return wfClient.getModuleList();
    }

}
