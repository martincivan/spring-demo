package com.example.demo.view;

import java.util.List;

public class TemperatureResponse {
    private List<String> labels;
    private List<Double> data;

    public TemperatureResponse(List<String> labels, List<Double> data) {
        this.labels = labels;
        this.data = data;
    }

    public List<String> getLabels() {
        return labels;
    }

    public List<Double> getData() {
        return data;
    }
}