package com.example.meuapp.model;

public class WeatherData {
    private final String title;
    private final String subtitle;

    public WeatherData(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }

    public String getTitle() { return title; }
    public String getSubtitle() { return subtitle; }
}
