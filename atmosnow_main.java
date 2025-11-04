package com.michaelsemera.atmosnow;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * AtmosNow - Advanced Weather Forecast Application
 * 
 * @author Michael Semera
 * @version 1.0
 * 
 * A JavaFX application that fetches real-time weather data from OpenWeatherMap API
 * and displays comprehensive forecasts with interactive charts and detailed metrics.
 */
public class AtmosNowApp extends Application {
    
    private WeatherService weatherService;
    private TextField cityTextField;
    private Label currentTempLabel;
    private Label conditionLabel;
    private Label humidityLabel;
    private Label windLabel;
    private Label pressureLabel;
    private Label feelsLikeLabel;
    private Label sunriseLabel;
    private Label sunsetLabel;
    private Label lastUpdateLabel;
    private LineChart<String, Number> tempChart;
    private BarChart<String, Number> precipitationChart;
    private AreaChart<String, Number> humidityChart;
    private ProgressBar loadingBar;
    private VBox chartContainer;
    
    private static final String APP_TITLE = "AtmosNow - Weather Forecast";
    private static final String DEFAULT_CITY = "London";
    private static final int WINDOW_WIDTH = 1200;
    private static final int WINDOW_HEIGHT = 800;
    
    @Override
    public void start(Stage primaryStage) {
        weatherService = new WeatherService();
        
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #1e3c72, #2a5298);");
        
        // Top section - Search and title
        VBox topSection = createTopSection();
        root.setTop(topSection);
        
        // Center section - Current weather and charts
        VBox centerSection = createCenterSection();
        root.setCenter(centerSection);
        
        // Bottom section - Additional info
        HBox bottomSection = createBottomSection();
        root.setBottom(bottomSection);
        
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        
        primaryStage.setTitle(APP_TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // Load default city weather
        loadWeatherData(DEFAULT_CITY);
    }
    
    /**
     * Creates the top section with title and search functionality
     */
    private VBox createTopSection() {
        VBox topBox = new VBox(15);
        topBox.setPadding(new Insets(20));
        topBox.setAlignment(Pos.CENTER);
        topBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.1);");
        
        // Title
        Label titleLabel = new Label("AtmosNow");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        titleLabel.setStyle("-fx-text-fill: white;");
        
        // Subtitle
        Label subtitleLabel = new Label("Real-Time Weather Intelligence");
        subtitleLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        subtitleLabel.setStyle("-fx-text-fill: #e0e0e0;");
        
        // Search box
        HBox searchBox = new HBox(10);
        searchBox.setAlignment(Pos.CENTER);
        searchBox.setMaxWidth(500);
        
        cityTextField = new TextField(DEFAULT_CITY);
        cityTextField.setPromptText("Enter city name...");
        cityTextField.setPrefWidth(350);
        cityTextField.setStyle("-fx-font-size: 14px; -fx-padding: 10px;");
        cityTextField.setOnAction(e -> searchWeather());
        
        Button searchButton = new Button("Search");
        searchButton.setPrefWidth(120);
        searchButton.setStyle("-fx-font-size: 14px; -fx-background-color: #4CAF50; " +
                            "-fx-text-fill: white; -fx-padding: 10px;");
        searchButton.setOnAction(e -> searchWeather());
        
        Button refreshButton = new Button("🔄");
        refreshButton.setPrefWidth(50);
        refreshButton.setStyle("-fx-font-size: 16px; -fx-background-color: #2196F3; " +
                             "-fx-text-fill: white; -fx-padding: 10px;");
        refreshButton.setOnAction(e -> loadWeatherData(cityTextField.getText()));
        
        searchBox.getChildren().addAll(cityTextField, searchButton, refreshButton);
        
        // Loading bar
        loadingBar = new ProgressBar(0);
        loadingBar.setPrefWidth(500);
        loadingBar.setVisible(false);
        
        topBox.getChildren().addAll(titleLabel, subtitleLabel, searchBox, loadingBar);
        return topBox;
    }
    
    /**
     * Creates the center section with current weather and charts
     */
    private VBox createCenterSection() {
        VBox centerBox = new VBox(20);
        centerBox.setPadding(new Insets(20));
        centerBox.setAlignment(Pos.TOP_CENTER);
        
        // Current weather display
        VBox currentWeatherBox = createCurrentWeatherBox();
        
        // Charts container
        chartContainer = new VBox(15);
        chartContainer.setAlignment(Pos.CENTER);
        
        centerBox.getChildren().addAll(currentWeatherBox, chartContainer);
        return centerBox;
    }
    
    /**
     * Creates the current weather information display
     */
    private VBox createCurrentWeatherBox() {
        VBox weatherBox = new VBox(10);
        weatherBox.setAlignment(Pos.CENTER);
        weatherBox.setPadding(new Insets(20));
        weatherBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.15); " +
                          "-fx-background-radius: 15px;");
        weatherBox.setMaxWidth(800);
        
        currentTempLabel = new Label("--°C");
        currentTempLabel.setFont(Font.font("Arial", FontWeight.BOLD, 64));
        currentTempLabel.setStyle("-fx-text-fill: white;");
        
        conditionLabel = new Label("Loading...");
        conditionLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 24));
        conditionLabel.setStyle("-fx-text-fill: #e0e0e0;");
        
        HBox detailsBox = new HBox(30);
        detailsBox.setAlignment(Pos.CENTER);
        
        feelsLikeLabel = createDetailLabel("Feels Like: --°C");
        humidityLabel = createDetailLabel("Humidity: --%");
        windLabel = createDetailLabel("Wind: -- m/s");
        pressureLabel = createDetailLabel("Pressure: -- hPa");
        
        detailsBox.getChildren().addAll(feelsLikeLabel, humidityLabel, windLabel, pressureLabel);
        
        weatherBox.getChildren().addAll(currentTempLabel, conditionLabel, detailsBox);
        return weatherBox;
    }
    
    /**
     * Creates the bottom section with sunrise/sunset and last update info
     */
    private HBox createBottomSection() {
        HBox bottomBox = new HBox(40);
        bottomBox.setPadding(new Insets(20));
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.2);");
        
        sunriseLabel = createDetailLabel("🌅 Sunrise: --:--");
        sunsetLabel = createDetailLabel("🌇 Sunset: --:--");
        lastUpdateLabel = createDetailLabel("Last Updated: --");
        
        bottomBox.getChildren().addAll(sunriseLabel, sunsetLabel, lastUpdateLabel);
        return bottomBox;
    }
    
    /**
     * Creates a styled label for weather details
     */
    private Label createDetailLabel(String text) {
        Label label = new Label(text);
        label.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        label.setStyle("-fx-text-fill: white;");
        return label;
    }
    
    /**
     * Initiates weather search based on user input
     */
    private void searchWeather() {
        String city = cityTextField.getText().trim();
        if (!city.isEmpty()) {
            loadWeatherData(city);
        } else {
            showAlert("Invalid Input", "Please enter a city name.");
        }
    }
    
    /**
     * Loads weather data from the API and updates UI
     */
    private void loadWeatherData(String city) {
        loadingBar.setVisible(true);
        loadingBar.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
        
        // Simulating API call in background thread
        new Thread(() -> {
            try {
                WeatherData currentWeather = weatherService.getCurrentWeather(city);
                ForecastData forecast = weatherService.getForecast(city);
                
                // Update UI on JavaFX thread
                javafx.application.Platform.runLater(() -> {
                    updateCurrentWeather(currentWeather);
                    updateCharts(forecast);
                    updateAdditionalInfo(currentWeather);
                    loadingBar.setVisible(false);
                });
                
            } catch (Exception e) {
                javafx.application.Platform.runLater(() -> {
                    showAlert("Error", "Failed to fetch weather data: " + e.getMessage());
                    loadingBar.setVisible(false);
                });
            }
        }).start();
    }
    
    /**
     * Updates current weather display
     */
    private void updateCurrentWeather(WeatherData data) {
        currentTempLabel.setText(String.format("%.1f°C", data.getTemperature()));
        conditionLabel.setText(data.getCondition());
        feelsLikeLabel.setText(String.format("Feels Like: %.1f°C", data.getFeelsLike()));
        humidityLabel.setText(String.format("Humidity: %d%%", data.getHumidity()));
        windLabel.setText(String.format("Wind: %.1f m/s", data.getWindSpeed()));
        pressureLabel.setText(String.format("Pressure: %d hPa", data.getPressure()));
    }
    
    /**
     * Updates all weather charts with forecast data
     */
    private void updateCharts(ForecastData forecast) {
        chartContainer.getChildren().clear();
        
        // Temperature trend chart
        tempChart = createTemperatureChart(forecast);
        
        // Precipitation probability chart
        precipitationChart = createPrecipitationChart(forecast);
        
        // Humidity area chart
        humidityChart = createHumidityChart(forecast);
        
        HBox chartsRow = new HBox(15);
        chartsRow.setAlignment(Pos.CENTER);
        chartsRow.getChildren().addAll(tempChart, precipitationChart);
        
        chartContainer.getChildren().addAll(chartsRow, humidityChart);
    }
    
    /**
     * Creates temperature trend line chart
     */
    private LineChart<String, Number> createTemperatureChart(ForecastData forecast) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Time");
        yAxis.setLabel("Temperature (°C)");
        
        LineChart<String, Number> chart = new LineChart<>(xAxis, yAxis);
        chart.setTitle("5-Day Temperature Forecast");
        chart.setPrefSize(550, 300);
        chart.setStyle("-fx-background-color: rgba(255, 255, 255, 0.9);");
        
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Temperature");
        
        for (ForecastPoint point : forecast.getForecasts()) {
            series.getData().add(new XYChart.Data<>(point.getTimeLabel(), point.getTemperature()));
        }
        
        chart.getData().add(series);
        return chart;
    }
    
    /**
     * Creates precipitation probability bar chart
     */
    private BarChart<String, Number> createPrecipitationChart(ForecastData forecast) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Time");
        yAxis.setLabel("Precipitation (%)");
        
        BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);
        chart.setTitle("Precipitation Probability");
        chart.setPrefSize(550, 300);
        chart.setStyle("-fx-background-color: rgba(255, 255, 255, 0.9);");
        
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Rain Chance");
        
        for (ForecastPoint point : forecast.getForecasts()) {
            series.getData().add(new XYChart.Data<>(point.getTimeLabel(), point.getPrecipitation()));
        }
        
        chart.getData().add(series);
        return chart;
    }
    
    /**
     * Creates humidity area chart
     */
    private AreaChart<String, Number> createHumidityChart(ForecastData forecast) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Time");
        yAxis.setLabel("Humidity (%)");
        
        AreaChart<String, Number> chart = new AreaChart<>(xAxis, yAxis);
        chart.setTitle("Humidity Levels");
        chart.setPrefSize(1100, 250);
        chart.setStyle("-fx-background-color: rgba(255, 255, 255, 0.9);");
        
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Humidity");
        
        for (ForecastPoint point : forecast.getForecasts()) {
            series.getData().add(new XYChart.Data<>(point.getTimeLabel(), point.getHumidity()));
        }
        
        chart.getData().add(series);
        return chart;
    }
    
    /**
     * Updates sunrise, sunset, and last update information
     */
    private void updateAdditionalInfo(WeatherData data) {
        sunriseLabel.setText("🌅 Sunrise: " + data.getSunrise());
        sunsetLabel.setText("🌇 Sunset: " + data.getSunset());
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");
        lastUpdateLabel.setText("Last Updated: " + LocalDateTime.now().format(formatter));
    }
    
    /**
     * Displays an alert dialog
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}