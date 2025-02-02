package de.bcxp.challenge.weather;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WeatherDataAnalyserTest {

    @Test
    void shouldReturnExpectedDay() {
        List<WeatherData> weatherData = new ArrayList<>();
        weatherData.add(new WeatherData(1, 40, 20));
        weatherData.add(new WeatherData(2, 30, 20));
        weatherData.add(new WeatherData(3, 20, 20));

        var weatherDataAnalyser = new WeatherDataAnalyser();

        assertEquals(3, weatherDataAnalyser.findSmallestTemperatureSpread(weatherData).orElseGet(Assertions::fail).getDay());
    }

    @Test
    void shouldReturnEmptyWhenNoWeatherDataAvailable() {
        List<WeatherData> weatherData = new ArrayList<>();

        var weatherDataAnalyser = new WeatherDataAnalyser();

        assertFalse(weatherDataAnalyser.findSmallestTemperatureSpread(weatherData).isPresent());
    }

    @Test
    void shouldReturnFirstFindingWhenMinimumSpreadIsIdentical() {
        List<WeatherData> weatherData = new ArrayList<>();
        weatherData.add(new WeatherData(1, 20, 20));
        weatherData.add(new WeatherData(2, 20, 20));

        var weatherDataAnalyser = new WeatherDataAnalyser();

        assertEquals(1, weatherDataAnalyser.findSmallestTemperatureSpread(weatherData).orElseGet(Assertions::fail).getDay());
    }

    @Test
    void shouldCalculateCorrectTemperatureSpread() {
        WeatherData data = new WeatherData(1, 20, 10);
        assertEquals(10, data.getTemperatureSpread());
    }
}