package de.bcxp.challenge.countries;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class CountryDataAnalyser {

    public Optional<CountryData> findMostPeoplePerSquareKilometers(List<CountryData> data) {
        return data.stream().max(Comparator.comparing(CountryData::getPeoplePerSquareKilometer));
    }
}
