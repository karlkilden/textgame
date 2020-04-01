package com.kildeen.sys;

import org.hjson.JsonValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class ConfigReaderTest {

    private FileReader fr;
    List<String> list = List.of("{"+System.lineSeparator(), "  first: 1"+System.lineSeparator(), "  second:2", "}"+System.lineSeparator());
    private ConfigReader configReader;

    @BeforeEach
    void setUp() {
        fr = Mockito.mock(FileReader.class);
        when(fr.getLines(anyString())).thenReturn(list);
        configReader = new ConfigReader(fr);
    }

    @Test
    void read() {
        assertThat(configReader.read("test")).containsExactlyElementsOf(list);

    }

    @Test
    void read_as_hjson() {
        JsonValue config = configReader.readAsHjson("test");
        assertThat(config.asObject().getInt("first",0)).isEqualTo(1);
    }
}