package ru.iflex.maven.moduleinfo.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.iflex.maven.moduleinfo.model.FullInfo;
import ru.iflex.maven.moduleinfo.operations.GetModuleInfoList;

import java.util.List;

@Configuration
public class FullInfoBean {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public List<FullInfo> fullInfoListSingleton(GetModuleInfoList getModuleInfoList) {
        return getModuleInfoList.run();
    }

}
