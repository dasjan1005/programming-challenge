package de.bcxp.challenge.weather;

public class WeatherDataLoaderException extends RuntimeException {

    public WeatherDataLoaderException(String message, Exception cause) {
        super(message, cause);
    }
}
