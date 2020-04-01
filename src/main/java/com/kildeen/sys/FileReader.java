package com.kildeen.sys;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReader {

    public List<String> getLines(String pathToUse) {

        Stream<String> lines = null;
        try {
            Path path = Paths.get(pathToUse);
            lines = Files.lines(path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        List<String> asList = lines.collect(Collectors.toList());
        lines.close();
        return asList;
    }
}
