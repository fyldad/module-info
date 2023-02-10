package ru.iflex.maven.moduleinfo.client;

import lombok.extern.slf4j.Slf4j;
import org.jboss.as.cli.CommandContext;
import org.jboss.as.cli.CommandFormatException;
import org.jboss.as.cli.scriptsupport.CLI;
import org.jboss.as.controller.client.ModelControllerClient;
import org.jboss.dmr.ModelNode;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;


@Service
@Slf4j
public class WfClient {

    public List<String> getModuleList()  {
        CLI cli = CLI.newInstance();
        cli.connect();
        CommandContext ctx = cli.getCommandContext();

        Set<String> modules = getModuleList(ctx);
        List<String> moduleList = modules.stream()
                .map(app -> getContextRoot(ctx, app))
                .filter(module -> !"/module-info".equals(module) && !"undefined".equals(module))
                .toList();

        cli.disconnect();

        return moduleList;
    }

    private Set<String> getModuleList(CommandContext ctx) {
        ModelNode response;
        try {
            response = executeCliCommand(ctx, "deployment-info");
        } catch (CommandFormatException | IOException e) {
            log.error("error getting module list from local wildfly", e);
            return Collections.emptySet();
        }
        return response.keys();
    }

    private String getContextRoot(CommandContext ctx, String webApp) {
        ModelNode appResponse;
        try {
            appResponse = executeCliCommand(ctx, String.format(
                    "/deployment=%s/subsystem=undertow:read-attribute(name=context-root)", webApp));
        } catch (CommandFormatException | IOException e) {
            log.error("error getting context root for {}", webApp, e);
            return "undefined";
        }
        return appResponse.asString();
    }

    private ModelNode executeCliCommand(CommandContext ctx, String command) throws CommandFormatException, IOException {
        ModelControllerClient client = ctx.getModelControllerClient();
        ModelNode cliCommand = ctx.buildRequest(command);
        ModelNode response = client.execute(cliCommand);
        return response.get("result");
    }

}
