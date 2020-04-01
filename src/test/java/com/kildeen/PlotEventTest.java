package com.kildeen;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hjson.JsonObject;
import org.hjson.JsonValue;
import org.hjson.Stringify;
import org.junit.jupiter.api.Test;

import java.util.List;


class PlotEventTest {

    @Test
    void print_to_hjson() {
        PlotEvent pe = new PlotEvent();
        pe.setEventId("1");
        pe.setEventName("Select your character");
        pe.setAsOptionText("Go to select Character");
        pe.setEventText("Select your character. Chooce wisely, this choice is final.");
        pe.setOptions(List.of("2","3"));

        PlotEvent pe2 = new PlotEvent();
        pe2.setEventId("2");
        pe2.setEventName("You have selected - Warrior!");
        pe2.setAsOptionText("Warrior");
        pe2.setEventText("Any good warrior needs a weapon of choice");

        PlotEvent pe3 = new PlotEvent();
        pe3.setEventId("3");
        pe3.setEventName("You have selected - Wizard!");
        pe3.setAsOptionText("Wizard");
        pe3.setEventText("Any good wizard needs to select it's flair of magic");

        List<PlotEvent> firstEvents = List.of(pe,pe2,pe3);

        ObjectMapper mapper = new ObjectMapper();

        String jsonStr = null;
        try {
            jsonStr = mapper.writeValueAsString(firstEvents);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        String hjsonString = JsonValue.readHjson(jsonStr).toString(Stringify.HJSON);

        System.out.println(hjsonString);

        try {
            List<PlotEvent> myObjects = mapper.readValue(JsonValue.readHjson(hjsonString).toString(), new TypeReference<List<PlotEvent>>(){});
            System.out.println(hjsonString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }
}