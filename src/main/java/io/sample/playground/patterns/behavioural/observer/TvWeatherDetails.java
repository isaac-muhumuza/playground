package io.sample.playground.patterns.behavioural.observer;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class TvWeatherDetails implements WeatherDetails {
    private String weather;
    @Override
    public void update(String weather) {
        this.weather = weather;
        broadcastWeather();
    }

    private void broadcastWeather() {
        log.info("TV broadcasting updated weather of: {}", weather);
    }
}
