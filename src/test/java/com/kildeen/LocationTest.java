package com.kildeen;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class LocationTest {

    @Test
    void set_and_get() {
        Location location = new Location();
        location.setName("test");
        Assertions.assertThat(location.getName()).isEqualTo("test");
    }
}