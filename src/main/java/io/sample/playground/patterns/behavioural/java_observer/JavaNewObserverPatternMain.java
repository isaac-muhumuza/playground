package io.sample.playground.patterns.behavioural.java_observer;

public class JavaNewObserverPatternMain {

    public static void main (String[] args) {
        RadioWeatherStation radioWeatherStation = new RadioWeatherStation();
        TvChanel tvChanel = new TvChanel();

        ConcreteWeatherSubject observer = new ConcreteWeatherSubject();
        observer.addStation(radioWeatherStation);
        observer.addStation(tvChanel);

        observer.updateStationWeather("Sunny Today with 30 degrees");

        observer.updateStationWeather("Feels like 28 degrees due to the cool winds");
    }
}
