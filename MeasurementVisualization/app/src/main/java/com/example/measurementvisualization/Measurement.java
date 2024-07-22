package com.example.measurementvisualization;

public class Measurement {
    private String TIMESTAMP;
    private double measurement;
    private String ID;

    private String entry_id;

    public String getTimestamp() {
        return TIMESTAMP;
    }

    public double getValue() {
        return measurement;
    }

    public String getDevice_id(){
        return ID;
    }
}
