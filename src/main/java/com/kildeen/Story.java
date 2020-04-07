package com.kildeen;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hjson.JsonValue;
import org.hjson.Stringify;

import java.util.List;

public class Story {
    ObjectMapper mapper = new ObjectMapper();
    private List<PlotEvent> plotEvents;

    public void createStory(JsonValue hjson) {
        try {
            plotEvents = mapper.readValue(hjson.toString(), new TypeReference<List<PlotEvent>>(){});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public List<PlotEvent> getPlotEvents() {
        return plotEvents;
    }
}
