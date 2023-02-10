package ru.iflex.maven.moduleinfo.operations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.iflex.maven.moduleinfo.client.ModuleInfoClient;
import ru.iflex.maven.moduleinfo.client.WfClient;
import ru.iflex.maven.moduleinfo.model.FullInfo;
import ru.iflex.maven.moduleinfo.model.ModuleInfo;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetModuleInfoList {

    private final WfClient wfClient;
    private final ModuleInfoClient moduleInfoClient;

    public List<FullInfo> run () {
        List<String> modules = wfClient.getModuleList();
        List<FullInfo> response = new ArrayList<>();
        for (String module : modules) {
            ModuleInfo moduleInfo = moduleInfoClient.getModuleInfo(module);
            FullInfo fullInfo = new FullInfo();
            fullInfo.setName(module);
            fullInfo.setModuleInfo(moduleInfo);
            response.add(fullInfo);
        }
        return response;
    }

}
