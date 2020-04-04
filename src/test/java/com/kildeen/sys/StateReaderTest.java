package com.kildeen.sys;

import org.hjson.JsonValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class StateReaderTest {

    private FileReader fr;
    List<String> list = List.of("{"+System.lineSeparator(), "  first: 1"+System.lineSeparator(), "  second:2", "}"+System.lineSeparator());
    private StateReader stateReader;

    @BeforeEach
    void setUp() {
        fr = Mockito.mock(FileReader.class);
        when(fr.getLines(anyString())).thenReturn(list);
        stateReader = new StateReader(fr);
    }


    @Test
    void read_as_hjson() {
        JsonValue config = stateReader.readAsHjson("test");
        assertThat(config.asObject().getInt("first",0)).isEqualTo(1);
    }
}