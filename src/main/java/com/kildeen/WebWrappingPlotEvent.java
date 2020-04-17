package com.kildeen;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.HashMap;
import java.util.Map;

public class WebWrappingPlotEvent {

    private Map<String, String> childOptionsToChildOptionText;
    private PlotEvent plotEvent;



    public WebWrappingPlotEvent (PlotEvent plotEvent,Map<String, String> optionsToOptionsTexts){
        this.plotEvent = plotEvent;
        this.childOptionsToChildOptionText = new HashMap<>();
        if (this.plotEvent.getOptions() != null)
        for (String option : this.plotEvent.getOptions()) {
            if (option == null || option.isBlank() )    {
                continue;
            }
            this.childOptionsToChildOptionText.put(option, optionsToOptionsTexts.get(option));
        }
    }

    @Override
    public String toString() {
        ObjectMapper m = new ObjectMapper();
        try {
            return m.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, String> getChildOptionsToChildOptionText() {
        return childOptionsToChildOptionText;
    }

    public void setChildOptionsToChildOptionText(Map<String, String> childOptionsToChildOptionText) {
        this.childOptionsToChildOptionText = childOptionsToChildOptionText;
    }

    public PlotEvent getPlotEvent() {
        return plotEvent;
    }

    public void setPlotEvent(PlotEvent plotEvent) {
        this.plotEvent = plotEvent;
    }
}
