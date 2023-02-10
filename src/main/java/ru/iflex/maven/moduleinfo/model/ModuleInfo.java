package ru.iflex.maven.moduleinfo.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ModuleInfo {
    private boolean isSwaggerFound, isTracingFound, isThrottlingFound, isMetricsFound,
            isNewConfigCovered, isDependsOnCRMContract, isDependsOnESBSoapServices, isDependsOnCRMProxyContract;
    private List<Resource> resources;
}
