package de.bcxp.challenge.weather;

import org.junit.jupiter.api.Test;

import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvWeatherDataLoaderTest {

    @Test
    void shouldLoadCsvCorrectly() {
        WeatherDataLoader weatherDataLoader = new CsvWeatherDataLoader(Path.of("./src/test/resources/weather.csv"));

        List<WeatherData> data = weatherDataLoader.load();

        assertEquals(1, data.size());
        assertEquals(1, data.get(0).getDay());
        assertEquals(88, data.get(0).getMaxTemperature());
        assertEquals(59, data.get(0).getMinTemperature());
    }

    @Test
    void shouldThrowWeatherDataLoaderExceptionWhenFileDoesNotExist() {
        var exception = assertThrows(WeatherDataLoaderException.class, () -> new CsvWeatherDataLoader(Path.of("./src/test/resources/weathers.csv")).load());
        assertEquals(NoSuchFileException.class, exception.getCause().getClass());
    }

    @Test
    void shouldNotBreakWhenIsEmpty() {
        WeatherDataLoader weatherDataLoader = new CsvWeatherDataLoader(Path.of("./src/test/resources/weather_empty.csv"));

        List<WeatherData> data = weatherDataLoader.load();

        assertEquals(0, data.size());
    }
}
