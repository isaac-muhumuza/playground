package io.sample.playground.patterns.behavioural.observer;

public class ObserverPatternMain {

    public static void main (String[] args) {
        WeatherDetails phoneApp = new PhoneWeatherApp();
        WeatherDetails tvStaion = new TvWeatherDetails();

        ConcreteWeatherObserver observer = new ConcreteWeatherObserver();
        observer.addObserver(phoneApp);
        observer.addObserver(tvStaion);

        observer.updateWeather("Sunny Today with 30 degrees");

        observer.updateWeather("Feels like 28 degrees due to the cool winds");
    }
}
