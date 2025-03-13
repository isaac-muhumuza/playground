package io.sample.playground.patterns.behavioural.observer;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class PhoneWeatherApp implements WeatherDetails {

    private String weather;
    @Override
    public void update(String weather) {
        this.weather = weather;
        displayNewWeather();
    }

    private void displayNewWeather() {
        log.info("Phone Weather update is {}", weather);
    }
}
