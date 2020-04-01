package com.kildeen;

import java.util.List;


public class PlotEvent {
    private String eventName;
    private String eventId;
    private String eventText;
    private String asOptionText;

    private List<String> options;

    public String getAsOptionText() {
        return asOptionText;
    }

    public void setAsOptionText(String asOptionText) {
        this.asOptionText = asOptionText;
    }


    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventText() {
        return eventText;
    }

    public void setEventText(String eventText) {
        this.eventText = eventText;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}
