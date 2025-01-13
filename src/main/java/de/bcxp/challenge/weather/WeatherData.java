package de.bcxp.challenge.weather;

public class WeatherData {

    private final int day;
    private final int maxTemperature;
    private final int minTemperature;

    public WeatherData(int day, int maxTemperature, int minTemperature) {
        this.day = day;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
    }

    public int getDay() {
        return day;
    }

    public int getMaxTemperature() {
        return maxTemperature;
    }

    public int getMinTemperature() {
        return minTemperature;
    }

    public int getTemperatureSpread() {
        return maxTemperature - minTemperature;
    }
}
