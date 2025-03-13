package io.sample.playground.patterns.behavioural.observer;

public interface WeatherObserver {

    void addObserver(WeatherDetails weatherDetails);
    void removeObserver(WeatherDetails weatherDetails);

    void updateWeather(String weather);
}
