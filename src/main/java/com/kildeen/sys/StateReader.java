package com.kildeen.sys;

import org.hjson.JsonValue;

import java.util.List;
import java.util.stream.Collectors;

public class StateReader {

    private final FileReader fr;

    public StateReader(FileReader fr) {
        this.fr = fr;
    }


    public String path(String test) {
        return null;
    }

    public JsonValue readAsHjson(String path) {
        String fileContents = fr.getLines(path).stream().collect(Collectors.joining());
        return JsonValue.readHjson(fileContents);
    }
}
