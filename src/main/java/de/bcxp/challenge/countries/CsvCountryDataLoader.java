package de.bcxp.challenge.countries;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class CsvCountryDataLoader implements CountryDataLoader {

    private static final int COLUMN_NAME = 0;
    private static final int COLUMN_POPULATION = 3;
    private static final int COLUMN_AREA = 4;

    private final Path filename;

    public CsvCountryDataLoader(Path filename) {
        this.filename = filename;
    }

    @Override
    public List<CountryData> load() throws CountryDataLoaderException {
        try {
            CSVParser parser = new CSVParserBuilder().withSeparator(';').build();

            try (Reader reader = Files.newBufferedReader(this.filename);
                 CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).withCSVParser(parser).build()) {
                return csvReader.readAll().stream()
                        .map(line -> new CountryData(
                                line[COLUMN_NAME],
                                parsePopulation(line[COLUMN_POPULATION]),
                                Integer.parseInt(line[COLUMN_AREA])
                        )).collect(Collectors.toList());
            }
        } catch (Exception ex) {
            throw new CountryDataLoaderException("Cannot load country data!", ex);
        }
    }

    private int parsePopulation(String rawPopulation) {
        int population;

        try {
            population = NumberFormat.getIntegerInstance(Locale.GERMAN).parse(rawPopulation).intValue();
        } catch (ParseException e) {
            population = Integer.parseInt(rawPopulation);
        }
        return population;
    }
}
