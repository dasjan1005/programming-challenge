package de.bcxp.challenge.countries;

import java.util.List;

public interface CountryDataLoader {

    List<CountryData> load() throws CountryDataLoaderException;
}
