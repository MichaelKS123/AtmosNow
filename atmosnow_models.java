package com.michaelsemera.atmosnow;

import java.util.List;

/**
 * Model class representing current weather data
 * 
 * @author Michael Semera
 */
class WeatherData {
    private final String cityName;
    private final double temperature;
    private final double feelsLike;
    private final int humidity;
    private final int pressure;
    private final String condition;
    private final String description;
    private final double windSpeed;
    private final String sunrise;
    private final String sunset;
    
    public WeatherData(String cityName, double temperature, double feelsLike,
                      int humidity, int pressure, String condition,
                      String description, double windSpeed,
                      String sunrise, String sunset) {
        this.cityName = cityName;
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.humidity = humidity;
        this.pressure = pressure;
        this.condition = condition;
        this.description = description;
        this.windSpeed = windSpeed;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }
    
    public String getCityName() {
        return cityName;
    }
    
    public double getTemperature() {
        return temperature;
    }
    
    public double getFeelsLike() {
        return feelsLike;
    }
    
    public int getHumidity() {
        return humidity;
    }
    
    public int getPressure() {
        return pressure;
    }
    
    public String getCondition() {
        return condition;
    }
    
    public String getDescription() {
        return description;
    }
    
    public double getWindSpeed() {
        return windSpeed;
    }
    
    public String getSunrise() {
        return sunrise;
    }
    
    public String getSunset() {
        return sunset;
    }
    
    @Override
    public String toString() {
        return String.format("Weather in %s: %.1f°C (%s), Humidity: %d%%, Wind: %.1f m/s",
                cityName, temperature, condition, humidity, windSpeed);
    }
}

/**
 * Model class representing weather forecast data
 * 
 * @author Michael Semera
 */
class ForecastData {
    private final List<ForecastPoint> forecasts;
    
    public ForecastData(List<ForecastPoint> forecasts) {
        this.forecasts = forecasts;
    }
    
    public List<ForecastPoint> getForecasts() {
        return forecasts;
    }
    
    public int getForecastCount() {
        return forecasts.size();
    }
}

/**
 * Model class representing a single forecast point in time
 * 
 * @author Michael Semera
 */
class ForecastPoint {
    private final String timeLabel;
    private final double temperature;
    private final int humidity;
    private final double precipitation;
    private final String condition;
    
    public ForecastPoint(String timeLabel, double temperature, int humidity,
                        double precipitation, String condition) {
        this.timeLabel = timeLabel;
        this.temperature = temperature;
        this.humidity = humidity;
        this.precipitation = precipitation;
        this.condition = condition;
    }
    
    public String getTimeLabel() {
        return timeLabel;
    }
    
    public double getTemperature() {
        return temperature;
    }
    
    public int getHumidity() {
        return humidity;
    }
    
    public double getPrecipitation() {
        return precipitation;
    }
    
    public String getCondition() {
        return condition;
    }
    
    @Override
    public String toString() {
        return String.format("%s: %.1f°C, %d%% humidity, %.0f%% rain",
                timeLabel, temperature, humidity, precipitation);
    }
}