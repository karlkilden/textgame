package com.kildeen;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StoryTellerTest {

    @Test
    void tell_writes_something() {
        StoryTeller teller = new StoryTeller();
        Assertions.assertThat(teller.tell(new PlotEvent())).isNotEmpty();

    }
}