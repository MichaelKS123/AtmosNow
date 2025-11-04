package com.michaelsemera.atmosnow;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class for fetching weather data from OpenWeatherMap API
 * 
 * @author Michael Semera
 */
public class WeatherService {
    
    private static final String API_KEY = "YOUR_API_KEY_HERE"; // Replace with your OpenWeatherMap API key
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    private static final String CURRENT_WEATHER_ENDPOINT = "weather";
    private static final String FORECAST_ENDPOINT = "forecast";
    
    /**
     * Fetches current weather data for a given city
     * 
     * @param city The name of the city
     * @return WeatherData object containing current weather information
     * @throws Exception if API call fails
     */
    public WeatherData getCurrentWeather(String city) throws Exception {
        String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);
        String urlString = String.format("%s%s?q=%s&appid=%s&units=metric",
                BASE_URL, CURRENT_WEATHER_ENDPOINT, encodedCity, API_KEY);
        
        String jsonResponse = makeApiCall(urlString);
        return parseCurrentWeather(jsonResponse);
    }
    
    /**
     * Fetches 5-day weather forecast for a given city
     * 
     * @param city The name of the city
     * @return ForecastData object containing forecast information
     * @throws Exception if API call fails
     */
    public ForecastData getForecast(String city) throws Exception {
        String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);
        String urlString = String.format("%s%s?q=%s&appid=%s&units=metric",
                BASE_URL, FORECAST_ENDPOINT, encodedCity, API_KEY);
        
        String jsonResponse = makeApiCall(urlString);
        return parseForecast(jsonResponse);
    }
    
    /**
     * Makes HTTP GET request to the API
     * 
     * @param urlString The complete URL for the API call
     * @return JSON response as a string
     * @throws Exception if connection fails
     */
    private String makeApiCall(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        
        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new Exception("API returned error code: " + responseCode);
        }
        
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(connection.getInputStream())
        );
        
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        
        return response.toString();
    }
    
    /**
     * Parses current weather JSON response
     * 
     * @param jsonResponse Raw JSON string from API
     * @return Parsed WeatherData object
     */
    private WeatherData parseCurrentWeather(String jsonResponse) {
        JSONObject json = new JSONObject(jsonResponse);
        
        String cityName = json.getString("name");
        
        JSONObject main = json.getJSONObject("main");
        double temperature = main.getDouble("temp");
        double feelsLike = main.getDouble("feels_like");
        int humidity = main.getInt("humidity");
        int pressure = main.getInt("pressure");
        
        JSONArray weatherArray = json.getJSONArray("weather");
        JSONObject weather = weatherArray.getJSONObject(0);
        String condition = weather.getString("main");
        String description = weather.getString("description");
        
        JSONObject wind = json.getJSONObject("wind");
        double windSpeed = wind.getDouble("speed");
        
        JSONObject sys = json.getJSONObject("sys");
        long sunriseTimestamp = sys.getLong("sunrise");
        long sunsetTimestamp = sys.getLong("sunset");
        
        String sunrise = formatTimestamp(sunriseTimestamp);
        String sunset = formatTimestamp(sunsetTimestamp);
        
        return new WeatherData(
            cityName, temperature, feelsLike, humidity, pressure,
            condition, description, windSpeed, sunrise, sunset
        );
    }
    
    /**
     * Parses forecast JSON response
     * 
     * @param jsonResponse Raw JSON string from API
     * @return Parsed ForecastData object
     */
    private ForecastData parseForecast(String jsonResponse) {
        JSONObject json = new JSONObject(jsonResponse);
        JSONArray list = json.getJSONArray("list");
        
        List<ForecastPoint> forecasts = new ArrayList<>();
        
        // Process forecast data (take every 8th entry for daily data, or all for 3-hour intervals)
        for (int i = 0; i < Math.min(list.length(), 40); i++) {
            JSONObject item = list.getJSONObject(i);
            
            long timestamp = item.getLong("dt");
            String timeLabel = formatForecastTime(timestamp);
            
            JSONObject main = item.getJSONObject("main");
            double temp = main.getDouble("temp");
            int humidity = main.getInt("humidity");
            
            // Get precipitation probability
            double precipitation = 0;
            if (item.has("pop")) {
                precipitation = item.getDouble("pop") * 100; // Convert to percentage
            }
            
            JSONArray weatherArray = item.getJSONArray("weather");
            JSONObject weather = weatherArray.getJSONObject(0);
            String condition = weather.getString("main");
            
            forecasts.add(new ForecastPoint(timeLabel, temp, humidity, precipitation, condition));
        }
        
        return new ForecastData(forecasts);
    }
    
    /**
     * Formats Unix timestamp to readable time string
     * 
     * @param timestamp Unix timestamp in seconds
     * @return Formatted time string (HH:mm)
     */
    private String formatTimestamp(long timestamp) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(
            Instant.ofEpochSecond(timestamp),
            ZoneId.systemDefault()
        );
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return dateTime.format(formatter);
    }
    
    /**
     * Formats timestamp for forecast labels
     * 
     * @param timestamp Unix timestamp in seconds
     * @return Formatted date-time string (MMM dd HH:mm)
     */
    private String formatForecastTime(long timestamp) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(
            Instant.ofEpochSecond(timestamp),
            ZoneId.systemDefault()
        );
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd HH:mm");
        return dateTime.format(formatter);
    }
}