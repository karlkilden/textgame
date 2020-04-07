package com.kildeen;

import com.kildeen.sys.FileReader;
import com.kildeen.sys.StateReader;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;


class StoryTest {

    @Test
    void createStory() {
        Story s = new Story();
        StateReader reader = new StateReader(new FileReader());
        s.createStory(reader.readAsHjson("src/test/resources/test-game.hjson"));
        Assertions.assertThat(s.getPlotEvents()).isNotEmpty();
        Assertions.assertThat(s.getPlotEvents()).extractingResultOf("getEventId").contains("1","2", "3");
    }
}