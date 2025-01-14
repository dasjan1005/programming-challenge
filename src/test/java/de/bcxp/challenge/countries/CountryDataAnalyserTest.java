package de.bcxp.challenge.countries;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CountryDataAnalyserTest {

    @Test
    void shouldReturnExpectedCountry() {
        List<CountryData> countryData = new ArrayList<>();
        countryData.add(new CountryData("Austria", 8926000, 83855));
        countryData.add(new CountryData("Belgium", 11566041, 30528));
        countryData.add(new CountryData("Bulgaria", 6916548, 110994));

        var countryDataAnalyser = new CountryDataAnalyser();

        assertEquals("Belgium", countryDataAnalyser.findMostPeoplePerSquareKilometers(countryData).orElseGet(Assertions::fail).getName());
    }

    @Test
    void shouldReturnEmptyWhenNoCountryDataAvailable() {
        List<CountryData> countryData = new ArrayList<>();

        var countryDataAnalyser = new CountryDataAnalyser();

        assertFalse(countryDataAnalyser.findMostPeoplePerSquareKilometers(countryData).isPresent());
    }

    @Test
    void shouldCalculateCorrectPopulationDensity() {
        CountryData austriaData = new CountryData("Austria", 10, 5);

        assertEquals(2, austriaData.getPeoplePerSquareKilometer());
    }
}