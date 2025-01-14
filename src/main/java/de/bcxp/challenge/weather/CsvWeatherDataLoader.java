package de.bcxp.challenge.weather;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class CsvWeatherDataLoader implements WeatherDataLoader {

    private static final int COLUMN_DAY = 0;
    private static final int COLUMN_MAX_TEMPERATURE = 1;
    private static final int COLUMN_MIN_TEMPERATURE = 2;

    private final Path filename;

    public CsvWeatherDataLoader(Path filename) {
        this.filename = filename;
    }

    @Override
    public List<WeatherData> load() throws WeatherDataLoaderException {
        try {
            try (Reader reader = Files.newBufferedReader(this.filename);
                 CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build()) {
                return csvReader.readAll().stream()
                        .map(line -> new WeatherData(
                                Integer.parseInt(line[COLUMN_DAY]),
                                Integer.parseInt(line[COLUMN_MAX_TEMPERATURE]),
                                Integer.parseInt(line[COLUMN_MIN_TEMPERATURE])
                        )).collect(Collectors.toList());
            }
        } catch (Exception ex) {
            throw new WeatherDataLoaderException("Cannot load weather data!", ex);
        }
    }
}
