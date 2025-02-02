package de.bcxp.challenge;

import de.bcxp.challenge.countries.CountryData;
import de.bcxp.challenge.countries.CountryDataAnalyser;
import de.bcxp.challenge.countries.CountryDataLoader;
import de.bcxp.challenge.countries.CsvCountryDataLoader;
import de.bcxp.challenge.weather.CsvWeatherDataLoader;
import de.bcxp.challenge.weather.WeatherData;
import de.bcxp.challenge.weather.WeatherDataAnalyser;
import de.bcxp.challenge.weather.WeatherDataLoader;

import java.nio.file.Path;
import java.util.List;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 */
public final class App {

    /**
     * This is the main entry method of your program.
     *
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {
        // Your preparation code …
        if (args.length == 0) {
            System.err.print("No argument found! Cannot read data...");
            System.exit(1);
        }

        WeatherDataLoader weatherDataLoader = new CsvWeatherDataLoader(Path.of(args[0]));
        WeatherDataAnalyser weatherDataAnalyser = new WeatherDataAnalyser();

        try {
            List<WeatherData> weatherData = weatherDataLoader.load();

            String dayWithSmallestTempSpread = weatherDataAnalyser.findSmallestTemperatureSpread(weatherData)
                    .map(WeatherData::getDay).map(Object::toString)
                    .orElseThrow(() -> new RuntimeException("Empty weather data!"));     // Your day analysis function call …
            System.out.printf("Day with smallest temperature spread: %s%n", dayWithSmallestTempSpread);
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            System.exit(1);
        }

        if (args.length < 2) {
            System.err.print("No second argument found! Cannot read country data...");
            System.exit(1);
        }

        CountryDataLoader countryDataLoader = new CsvCountryDataLoader(Path.of(args[1]));
        CountryDataAnalyser countryDataAnalyser = new CountryDataAnalyser();

        try {
            List<CountryData> countryData = countryDataLoader.load();

            String countryWithHighestPopulationDensity = countryDataAnalyser.findMostPeoplePerSquareKilometers(countryData)
                    .map(CountryData::getName).map(Object::toString)
                    .orElseThrow(() -> new RuntimeException("Empty country data!"));    // Your population density analysis function call …
            System.out.printf("Country with highest population density: %s%n", countryWithHighestPopulationDensity);
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            System.exit(1);
        }
    }
}
