package io.sample.playground.patterns.behavioural.java_observer;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

@Setter
@Getter
@Slf4j
public class TvChanel implements PropertyChangeListener {
    private String weather;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.setWeather((String) evt.getNewValue());
        log.info("TV broadcasting new updated weather of: {}", weather);
    }
}
