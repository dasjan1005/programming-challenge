package de.bcxp.challenge.weather;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class WeatherDataAnalyser {

    public Optional<WeatherData> findSmallestTemperatureSpread(List<WeatherData> data) {
        return data.stream().min(Comparator.comparing(WeatherData::getTemperatureSpread));
    }
}
