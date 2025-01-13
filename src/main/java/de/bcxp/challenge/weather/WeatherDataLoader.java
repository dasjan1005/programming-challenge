package de.bcxp.challenge.weather;

import java.util.List;

public interface WeatherDataLoader {

    List<WeatherData> load() throws WeatherDataLoaderException;
}
