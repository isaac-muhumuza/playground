package io.sample.playground.patterns.behavioural.java_observer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ConcreteWeatherSubject {
    private String weather;

    private PropertyChangeSupport propertyChangeSupport;

    public ConcreteWeatherSubject() {
        propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public void addStation(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removeStation(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    public void updateStationWeather(String weather) {
        propertyChangeSupport.firePropertyChange("new weather", this.weather, weather);
    }
}
