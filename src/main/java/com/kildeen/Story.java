package com.kildeen;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hjson.JsonValue;
import org.hjson.Stringify;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Story {
    ObjectMapper mapper = new ObjectMapper();
    private List<PlotEvent> plotEvents;
    private List<WebWrappingPlotEvent> webWrappingPlotEvents;

    private Map<String, String> optionsToOptionsText;

    public void createStory(JsonValue hjson) {
        try {
            plotEvents = mapper.readValue(hjson.toString(), new TypeReference<List<PlotEvent>>(){});
            optionsToOptionsText = plotEvents.stream()
                    .collect( Collectors.toMap(PlotEvent::getEventId,
                            PlotEvent::getAsOptionText) );
            webWrappingPlotEvents = plotEvents.stream().map(e -> new WebWrappingPlotEvent(e, optionsToOptionsText)).collect(Collectors.toList());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public List<PlotEvent> getPlotEvents() {
        return plotEvents;
    }

    public List<WebWrappingPlotEvent> getWebWrappingPlotEvents() {
        return webWrappingPlotEvents;
    }

    public WebWrappingPlotEvent getPlotEvent(String eventId) {
        return webWrappingPlotEvents.stream().filter(e -> e.getPlotEvent().getEventId().equals(eventId)).findFirst().get();
    }
}
