package ru.iflex.maven.moduleinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ModuleInfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleInfoApplication.class, args);
    }

}
