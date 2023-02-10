package ru.iflex.maven.moduleinfo.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Resource {
    private String name;
    private ResourceType type;
    private List<Method> methods = new ArrayList<>();
}
