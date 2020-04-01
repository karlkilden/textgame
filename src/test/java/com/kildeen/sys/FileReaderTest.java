package com.kildeen.sys;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderTest {

    @Test
    void read_returns_file() {
        FileReader fr = new FileReader();
        List<String> fileContents = fr.getLines("src/test/resources/test-config.hjson");

        Assertions.assertThat(fileContents).contains("  first: 1", "  second: 2");

    }
}