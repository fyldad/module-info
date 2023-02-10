package ru.iflex.maven.moduleinfo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Method {
    String method;
    String[] query;
    String tableName;
}
