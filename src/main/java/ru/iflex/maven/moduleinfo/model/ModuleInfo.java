package ru.iflex.maven.moduleinfo.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class ModuleInfo {
    private boolean isSwaggerFound, isTracingFound, isThrottlingFound, isMetricsFound,
            isNewConfigCovered, isDependsOnCRMContract, isDependsOnESBSoapServices, isDependsOnCRMProxyContract;
    private List<Resource> resources;
}
