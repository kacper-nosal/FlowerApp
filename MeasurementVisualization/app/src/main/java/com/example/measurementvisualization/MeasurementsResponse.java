package com.example.measurementvisualization;

import java.util.List;

public class MeasurementsResponse {
    private List<Measurement> value;
    private String nextLink;

    public List<Measurement> getValue() {
        return value;
    }
    public String getNextLink(){return nextLink;}

}
