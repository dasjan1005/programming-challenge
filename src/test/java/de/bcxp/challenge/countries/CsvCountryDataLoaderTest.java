package de.bcxp.challenge.countries;

import de.bcxp.challenge.weather.CsvWeatherDataLoader;
import de.bcxp.challenge.weather.WeatherData;
import de.bcxp.challenge.weather.WeatherDataLoader;
import de.bcxp.challenge.weather.WeatherDataLoaderException;
import org.junit.jupiter.api.Test;

import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvCountryDataLoaderTest {

    @Test
    void givenCsvFile() {
        CountryDataLoader countryDataLoader = new CsvCountryDataLoader(Path.of("./src/test/resources/countries.csv"));

        List<CountryData> data = countryDataLoader.load();

        assertEquals(27, data.size());
        assertEquals("Austria", data.get(0).getName());
        assertEquals(8926000, data.get(0).getPopulation());
        assertEquals(83855, data.get(0).getArea());
    }

    @Test
    void givenNonExistingCsvFile() {
        var exception = assertThrows(CountryDataLoaderException.class, () -> new CsvCountryDataLoader(Path.of("./src/test/resources/country.csv")).load());
        assertEquals(NoSuchFileException.class, exception.getCause().getClass());
    }

    @Test
    void givenDifferentDataFormat() {

        CountryDataLoader countryDataLoader = new CsvCountryDataLoader(Path.of("./src/test/resources/countries.csv"));

        List<CountryData> data = countryDataLoader.load();

        assertEquals(27, data.size());
        assertEquals("Croatia", data.get(3).getName());
        assertEquals(4036355, data.get(3).getPopulation());
        assertEquals(56594, data.get(3).getArea());
    }
}