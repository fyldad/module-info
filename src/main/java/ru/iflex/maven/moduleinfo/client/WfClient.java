package ru.iflex.maven.moduleinfo.client;

import org.jboss.as.cli.CommandContext;
import org.jboss.as.cli.CommandFormatException;
import org.jboss.as.cli.scriptsupport.CLI;
import org.jboss.as.controller.client.ModelControllerClient;
import org.jboss.dmr.ModelNode;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class WfClient {

    public List<String> getModuleList() throws CommandFormatException, IOException {
        CLI cli = CLI.newInstance();
        cli.connect();
        CommandContext ctx = cli.getCommandContext();
        ModelControllerClient client = ctx.getModelControllerClient();
        ModelNode cliCommand = ctx.buildRequest("deployment-info"); //get module list
        ModelNode response = client.execute(cliCommand);
        ModelNode result = response.get("result");
        Set<String> webapps = result.keys();

        List<String> moduleList = new ArrayList<>();

        for (String webApp : webapps) { //get context root for each module
            String strCommand = String.format("/deployment=%s/subsystem=undertow:read-attribute(name=context-root)", webApp);
            ModelNode appCommand = ctx.buildRequest(strCommand);
            ModelControllerClient appClient = ctx.getModelControllerClient();
            ModelNode appResult = appClient.execute(appCommand);
            String path = appResult.get("result").asString();
            if ("undefined".equals(path)) {
                path = "/" + webApp.substring(0, webApp.lastIndexOf('.'));
            }
            moduleList.add(path);
        }
        cli.disconnect();

        return moduleList;
    }

}
