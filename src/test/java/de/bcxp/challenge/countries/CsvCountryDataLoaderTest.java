package de.bcxp.challenge.countries;

import org.junit.jupiter.api.Test;

import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvCountryDataLoaderTest {

    @Test
    void shouldLoadCsvCorrectly() {
        CountryDataLoader countryDataLoader = new CsvCountryDataLoader(Path.of("./src/test/resources/countries.csv"));

        List<CountryData> data = countryDataLoader.load();

        assertEquals(27, data.size());
        assertEquals("Austria", data.get(0).getName());
        assertEquals(8926000, data.get(0).getPopulation());
        assertEquals(83855, data.get(0).getArea());
    }

    @Test
    void shouldThrowCountryDataLoaderExceptionWhenFileDoesNotExist() {
        var exception = assertThrows(CountryDataLoaderException.class, () -> new CsvCountryDataLoader(Path.of("./src/test/resources/country.csv")).load());
        assertEquals(NoSuchFileException.class, exception.getCause().getClass());
    }

    @Test
    void shouldLoadCsvCorrectlyEvenWithDifferentDataFormat() {

        CountryDataLoader countryDataLoader = new CsvCountryDataLoader(Path.of("./src/test/resources/countries_different_format.csv"));

        List<CountryData> data = countryDataLoader.load();

        assertEquals(4, data.size());
        assertEquals("Austria", data.get(0).getName());
        assertEquals(8926000, data.get(0).getPopulation());
        assertEquals(83855, data.get(0).getArea());
    }

    @Test
    void shouldThrowCountryDataLoaderExceptionWhenDataFormatIsInvalid() {
        var exception = assertThrows(CountryDataLoaderException.class, () -> new CsvCountryDataLoader(Path.of("./src/test/resources/countries_invalid.csv")).load());
        assertEquals(ParseException.class, exception.getCause().getClass());
    }

    @Test
    void shouldNotBreakWhenIsEmpty() {
        CountryDataLoader countryDataLoader = new CsvCountryDataLoader(Path.of("./src/test/resources/countries_empty.csv"));

        List<CountryData> data = countryDataLoader.load();

        assertEquals(0, data.size());
    }
}