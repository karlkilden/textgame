package com.kildeen;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class WebWrappingPlotEventTest {

    private PlotEvent event;
    private WebWrappingPlotEvent wEvent;
    private Map<String, String> optionsToTexts;

    @BeforeEach
    void setUp() {
        event = new PlotEvent();
        event.setOptions(Lists.list("4", "5", "6", null, ""));
        optionsToTexts = Map.of("4", "four", "5", "five", "6", "six", "7", "seven");

    }

    @Test
    void should_init_options_text() {

        wEvent = new WebWrappingPlotEvent(event, optionsToTexts);
        assertMap("4", "four");
        assertMap("5", "five");
        assertMap("6", "six");
        assertThat(wEvent.getChildOptionsToChildOptionText().get("7")).isNull();


    }

    private void assertMap(String key, String valueToMatch) {
        assertThat(wEvent.getChildOptionsToChildOptionText().get(key)).isEqualTo(valueToMatch);

    }
    @Test
    void should_ignore_null_and_empty_on_init() {
        wEvent = new WebWrappingPlotEvent(event, optionsToTexts);
        assertThat(wEvent.getChildOptionsToChildOptionText().keySet()).doesNotContain("", null);
    }
}