package com.kildeen;

import java.util.List;

public class Plot {

    private String name;
    private List<PlotEvent> plotEvents;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PlotEvent> getPlotEvents() {
        return plotEvents;
    }

    public void setPlotEvents(List<PlotEvent> plotEvents) {
        this.plotEvents = plotEvents;
    }
}
