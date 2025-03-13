package io.sample.playground.patterns.behavioural.observer;

import java.util.ArrayList;
import java.util.List;

public class ConcreteWeatherObserver implements WeatherObserver {
    List<WeatherDetails> list = new ArrayList<>();
    @Override
    public void addObserver(WeatherDetails weatherDetails) {
        list.add(weatherDetails);
    }

    @Override
    public void removeObserver(WeatherDetails weatherDetails) {
        list.remove(weatherDetails);
    }

    @Override
    public void updateWeather(String weather) {
        list.forEach(weatherDetails -> weatherDetails.update(weather));
    }
}
