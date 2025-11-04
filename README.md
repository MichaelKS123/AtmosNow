# AtmosNow ⛅

**Real-Time Weather Intelligence Platform**

[![Java](https://img.shields.io/badge/Java-17+-orange.svg)](https://www.oracle.com/java/technologies/javase-downloads.html)
[![JavaFX](https://img.shields.io/badge/JavaFX-17+-blue.svg)](https://openjfx.io/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

*By Michael Semera*

---

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Screenshots](#screenshots)
- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [Architecture](#architecture)
- [API Integration](#api-integration)
- [Project Structure](#project-structure)
- [Technologies Used](#technologies-used)
- [Building the Project](#building-the-project)
- [Troubleshooting](#troubleshooting)
- [Future Enhancements](#future-enhancements)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

---

## 🌟 Overview

**AtmosNow** is a modern, feature-rich weather forecast application built with Java and JavaFX. It provides real-time weather data and comprehensive 5-day forecasts through an elegant, user-friendly interface with interactive data visualizations.

The application leverages the OpenWeatherMap API to deliver accurate weather information for any city worldwide, presenting data through beautiful charts and intuitive displays.

### Why AtmosNow?

- **Real-Time Data**: Live weather updates from OpenWeatherMap
- **Visual Analytics**: Interactive JavaFX charts for temperature, precipitation, and humidity
- **Modern UI**: Sleek gradient design with smooth animations
- **Comprehensive Metrics**: Temperature, humidity, wind speed, pressure, sunrise/sunset
- **5-Day Forecast**: Detailed predictions with 3-hour intervals
- **Responsive Design**: Clean layout that adapts to different window sizes

---

## ✨ Features

### Core Functionality

1. **Current Weather Display**
   - Real-time temperature with "feels like" metric
   - Weather condition description
   - Humidity percentage
   - Wind speed in m/s
   - Atmospheric pressure in hPa
   - Sunrise and sunset times

2. **5-Day Weather Forecast**
   - 3-hour interval predictions
   - Up to 40 forecast data points
   - Temperature trends
   - Precipitation probability
   - Humidity variations

3. **Interactive Charts**
   - **Temperature Line Chart**: Visualizes temperature trends over time
   - **Precipitation Bar Chart**: Shows rain probability for each forecast period
   - **Humidity Area Chart**: Displays humidity level variations

4. **User Experience**
   - City search with auto-complete capability
   - Refresh button for manual updates
   - Loading indicator during API calls
   - Error handling with user-friendly alerts
   - Smooth transitions and animations

5. **Visual Design**
   - Beautiful gradient background (blue tones)
   - Glass-morphism effects on panels
   - Responsive layout
   - Professional typography
   - Color-coded charts for easy interpretation

---

## 📸 Screenshots

### Main Interface
```
┌────────────────────────────────────────────────────────┐
│                      AtmosNow                          │
│              Real-Time Weather Intelligence            │
│                                                        │
│  ┌──────────────────────────────────────────┐        │
│  │  [London            ] [Search] [🔄]      │        │
│  └──────────────────────────────────────────┘        │
│                                                        │
│  ┌────────────────────────────────────────────────┐  │
│  │                   18.5°C                       │  │
│  │                Partly Cloudy                   │  │
│  │  Feels: 17°C | Humidity: 65% | Wind: 3.5 m/s  │  │
│  └────────────────────────────────────────────────┘  │
│                                                        │
│  ┌──────────────────┐  ┌──────────────────┐         │
│  │ Temperature      │  │ Precipitation    │         │
│  │ Forecast Chart   │  │ Chart            │         │
│  └──────────────────┘  └──────────────────┘         │
│                                                        │
│  ┌────────────────────────────────────────────────┐  │
│  │        Humidity Levels Area Chart              │  │
│  └────────────────────────────────────────────────┘  │
│                                                        │
│  🌅 Sunrise: 06:24 | 🌇 Sunset: 20:15 | Updated: ... │
└────────────────────────────────────────────────────────┘
```

---

## 🚀 Installation

### Prerequisites

Before installing AtmosNow, ensure you have the following:

1. **Java Development Kit (JDK) 17 or higher**
   - Download from [Oracle](https://www.oracle.com/java/technologies/javase-downloads.html) or [OpenJDK](https://openjdk.org/)
   - Verify installation: `java -version`

2. **JavaFX SDK 17 or higher**
   - Download from [OpenJFX](https://openjfx.io/)
   - Or use Maven/Gradle dependencies

3. **Maven or Gradle** (optional, for dependency management)
   - Maven: [Download](https://maven.apache.org/download.cgi)
   - Gradle: [Download](https://gradle.org/install/)

4. **OpenWeatherMap API Key**
   - Register for free at [OpenWeatherMap](https://openweathermap.org/api)
   - Get your API key from the dashboard

### Clone the Repository

```bash
git clone https://github.com/yourusername/atmosnow.git
cd atmosnow
```

### Install Dependencies

#### Using Maven

Create a `pom.xml` file:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.michaelsemera</groupId>
    <artifactId>atmosnow</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>

    <name>AtmosNow</name>
    <description>Weather Forecast Application</description>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <javafx.version>17.0.2</javafx.version>
    </properties>

    <dependencies>
        <!-- JavaFX -->
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-controls</artifactId>
            <version>${javafx.version}</version>
        </dependency>
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-fxml</artifactId>
            <version>${javafx.version}</version>
        </dependency>

        <!-- JSON Processing -->
        <dependency>
            <groupId>org.json</groupId>
            <artifactId>json</artifactId>
            <version>20230227</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.openjfx</groupId>
                <artifactId>javafx-maven-plugin</artifactId>
                <version>0.0.8</version>
                <configuration>
                    <mainClass>com.michaelsemera.atmosnow.AtmosNowApp</mainClass>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

Then run:
```bash
mvn clean install
```

#### Using Gradle

Create a `build.gradle` file:

```gradle
plugins {
    id 'application'
    id 'org.openjfx.javafxplugin' version '0.0.13'
}

group = 'com.michaelsemera'
version = '1.0.0'
sourceCompatibility = '17'

repositories {
    mavenCentral()
}

javafx {
    version = "17.0.2"
    modules = ['javafx.controls', 'javafx.fxml']
}

dependencies {
    implementation 'org.json:json:20230227'
}

application {
    mainClass = 'com.michaelsemera.atmosnow.AtmosNowApp'
}
```

Then run:
```bash
gradle build
```

---

## ⚙️ Configuration

### Setting Up Your API Key

1. Open `WeatherService.java`
2. Locate the line:
   ```java
   private static final String API_KEY = "YOUR_API_KEY_HERE";
   ```
3. Replace `YOUR_API_KEY_HERE` with your actual OpenWeatherMap API key:
   ```java
   private static final String API_KEY = "abc123def456ghi789";
   ```

### Alternative: Environment Variable (Recommended)

For better security, use environment variables:

1. Modify `WeatherService.java`:
   ```java
   private static final String API_KEY = System.getenv("OPENWEATHER_API_KEY");
   ```

2. Set the environment variable:
   - **Windows (CMD)**:
     ```cmd
     set OPENWEATHER_API_KEY=your_api_key_here
     ```
   - **Windows (PowerShell)**:
     ```powershell
     $env:OPENWEATHER_API_KEY="your_api_key_here"
     ```
   - **Linux/Mac**:
     ```bash
     export OPENWEATHER_API_KEY=your_api_key_here
     ```

### CSS Stylesheet

The `styles.css` file should be placed in `src/main/resources/` directory. Ensure the path in `AtmosNowApp.java` matches:

```java
scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
```

---

## 💻 Usage

### Running the Application

#### Using Maven
```bash
mvn javafx:run
```

#### Using Gradle
```bash
gradle run
```

#### Using IDE (IntelliJ IDEA / Eclipse)
1. Open the project
2. Locate `AtmosNowApp.java`
3. Right-click → Run 'AtmosNowApp.main()'

#### Using Compiled JAR
```bash
java -jar atmosnow-1.0.0.jar
```

### Using the Application

1. **Search for a City**
   - Type city name in the search field
   - Press Enter or click "Search" button
   - Wait for data to load (loading bar appears)

2. **View Current Weather**
   - Main temperature display shows current conditions
   - Check "Feels Like" for perceived temperature
   - View humidity, wind speed, and pressure metrics

3. **Analyze Forecast Charts**
   - **Temperature Chart**: Hover over points for exact values
   - **Precipitation Chart**: View rain probability percentages
   - **Humidity Chart**: Track humidity trends over 5 days

4. **Refresh Data**
   - Click refresh button (🔄) to update weather
   - Application automatically loads default city (London) on startup

5. **Check Sunrise/Sunset**
   - View times at bottom of window
   - Times are displayed in local system timezone

---

## 🏗️ Architecture

### Design Pattern: MVC (Model-View-Controller)

```
┌─────────────────────────────────────────────────────┐
│                   AtmosNowApp                       │
│              (View + Controller)                    │
│  - User interface components                        │
│  - Event handling                                   │
│  - Chart management                                 │
└──────────────────┬──────────────────────────────────┘
                   │
                   │ Uses
                   ↓
┌─────────────────────────────────────────────────────┐
│               WeatherService                        │
│                 (Service Layer)                     │
│  - API communication                                │
│  - Data fetching                                    │
│  - JSON parsing                                     │
└──────────────────┬──────────────────────────────────┘
                   │
                   │ Creates
                   ↓
┌─────────────────────────────────────────────────────┐
│    WeatherData / ForecastData / ForecastPoint       │
│                   (Models)                          │
│  - Data representation                              │
│  - Encapsulation                                    │
│  - Immutable properties                             │
└─────────────────────────────────────────────────────┘
```

### Component Breakdown

#### 1. **AtmosNowApp.java** (View + Controller)
- **Responsibilities**:
  - UI construction and layout
  - User event handling
  - Chart creation and updates
  - Thread management for async operations
  
- **Key Methods**:
  - `createTopSection()`: Search bar and title
  - `createCenterSection()`: Weather display and charts
  - `createBottomSection()`: Sunrise/sunset info
  - `loadWeatherData()`: Async data fetching
  - `updateCharts()`: Chart data binding

#### 2. **WeatherService.java** (Service Layer)
- **Responsibilities**:
  - HTTP API communication
  - JSON response parsing
  - Error handling
  - Data transformation
  
- **Key Methods**:
  - `getCurrentWeather()`: Fetch current conditions
  - `getForecast()`: Fetch 5-day forecast
  - `makeApiCall()`: HTTP request handler
  - `parseCurrentWeather()`: JSON to model conversion

#### 3. **Data Models** (Model Layer)
- **WeatherData**: Current weather snapshot
- **ForecastData**: Collection of forecast points
- **ForecastPoint**: Single forecast timestamp

---

## 🌐 API Integration

### OpenWeatherMap API Endpoints

#### Current Weather
```
GET https://api.openweathermap.org/data/2.5/weather
Parameters:
  - q: City name (e.g., "London")
  - appid: Your API key
  - units: metric (Celsius) or imperial (Fahrenheit)
```

**Sample Response**:
```json
{
  "name": "London",
  "main": {
    "temp": 18.5,
    "feels_like": 17.2,
    "humidity": 65,
    "pressure": 1013
  },
  "weather": [
    {
      "main": "Clouds",
      "description": "partly cloudy"
    }
  ],
  "wind": {
    "speed": 3.5
  },
  "sys": {
    "sunrise": 1635395040,
    "sunset": 1635433200
  }
}
```

#### 5-Day Forecast
```
GET https://api.openweathermap.org/data/2.5/forecast
Parameters:
  - q: City name
  - appid: Your API key
  - units: metric
```

**Sample Response**:
```json
{
  "list": [
    {
      "dt": 1635408000,
      "main": {
        "temp": 19.2,
        "humidity": 62
      },
      "weather": [
        {
          "main": "Clear"
        }
      ],
      "pop": 0.15
    }
    // ... 39 more entries
  ]
}
```

### Rate Limits
- **Free Tier**: 60 calls/minute, 1,000,000 calls/month
- **Paid Tiers**: Higher limits available

### Error Handling

The application handles various API errors:
- Invalid API key (401)
- City not found (404)
- Rate limit exceeded (429)
- Network timeout
- Malformed JSON responses

---

## 📁 Project Structure

```
atmosnow/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── michaelsemera/
│   │   │           └── atmosnow/
│   │   │               ├── AtmosNowApp.java
│   │   │               ├── WeatherService.java
│   │   │               ├── WeatherData.java
│   │   │               ├── ForecastData.java
│   │   │               └── ForecastPoint.java
│   │   │
│   │   └── resources/
│   │       ├── styles.css
│   │       └── icons/
│   │           └── app-icon.png
│   │
│   └── test/
│       └── java/
│           └── com/
│               └── michaelsemera/
│                   └── atmosnow/
│                       └── WeatherServiceTest.java
│
├── pom.xml (or build.gradle)
├── README.md
├── LICENSE
└── .gitignore
```

---

## 🛠 Technologies Used

### Core Technologies

| Technology | Version | Purpose |
|------------|---------|---------|
| **Java** | 17+ | Core programming language |
| **JavaFX** | 17.0.2 | GUI framework |
| **JSON-Java** | 20230227 | JSON parsing |
| **OpenWeatherMap API** | 2.5 | Weather data source |

### JavaFX Components Used

- **Charts**: LineChart, BarChart, AreaChart
- **Controls**: Button, TextField, Label, ProgressBar
- **Layouts**: BorderPane, VBox, HBox
- **CSS**: Custom styling

### Development Tools

- **Maven/Gradle**: Dependency management
- **IntelliJ IDEA**: Recommended IDE
- **Scene Builder**: UI design (optional)

---

## 🔨 Building the Project

### Create Executable JAR

#### Using Maven
```bash
mvn clean package
```

Output: `target/atmosnow-1.0.0.jar`

#### Using Gradle
```bash
gradle build
```

Output: `build/libs/atmosnow-1.0.0.jar`

### Create Native Installer

#### Using jpackage (JDK 14+)

```bash
jpackage --input target/ \
         --name AtmosNow \
         --main-jar atmosnow-1.0.0.jar \
         --main-class com.michaelsemera.atmosnow.AtmosNowApp \
         --type exe \
         --icon src/main/resources/icons/app-icon.png \
         --app-version 1.0.0
```

This creates platform-specific installers:
- **Windows**: `.exe` or `.msi`
- **macOS**: `.dmg` or `.pkg`
- **Linux**: `.deb` or `.rpm`

---

## 🐛 Troubleshooting

### Common Issues

#### 1. **API Key Error (401)**
```
Error: API returned error code: 401
```
**Solution**: Verify your API key is correct in `WeatherService.java`

#### 2. **City Not Found (404)**
```
Error: API returned error code: 404
```
**Solution**: Check city name spelling; try major cities first

#### 3. **JavaFX Not Found**
```
Error: JavaFX runtime components are missing
```
**Solution**: 
- Add JavaFX to module path
- Or use Maven/Gradle dependencies
- Verify JavaFX SDK installation

#### 4. **JSON Parsing Error**
```
JSONException: JSONObject["key"] not found
```
**Solution**: 
- Check API response structure
- Verify internet connection
- Update JSON parsing logic if API changed

#### 5. **Network Timeout**
```
SocketTimeoutException: Read timed out
```
**Solution**:
- Check internet connection
- Increase timeout values in `WeatherService.java`
- Try again later if OpenWeatherMap servers are down

### Debug Mode

Enable debug logging:

```java
// Add to WeatherService.java
private static final boolean DEBUG = true;

if (DEBUG) {
    System.out.println("API URL: " + urlString);
    System.out.println("Response: " + jsonResponse);
}
```

---

## 🚀 Future Enhancements

### Planned Features

1. **Weather Alerts**
   - Severe weather notifications
   - Push notifications
   - Custom alert thresholds

2. **Multiple Locations**
   - Save favorite cities
   - Quick-switch between locations
   - Compare weather across cities

3. **Advanced Visualizations**
   - Wind direction compass
   - UV index gauge
   - Air quality indicator
   - Animated weather icons

4. **Historical Data**
   - Past weather trends
   - Temperature comparisons
   - Climate statistics

5. **Customization**
   - Theme selection (dark/light mode)
   - Unit preferences (C°/F°, mph/km/h)
   - Language localization

6. **Extended Forecast**
   - 14-day outlook
   - Hourly predictions
   - Minute-by-minute precipitation

7. **Widgets**
   - Desktop widget mode
   - System tray integration
   - Always-on-top option

8. **Export Features**
   - Save forecast as PDF
   - Export chart images
   - Share weather on social media

---

## 🤝 Contributing

Contributions are welcome! Here's how to contribute:

### Reporting Bugs

1. Check if the issue already exists
2. Create a new issue with:
   - Clear description
   - Steps to reproduce
   - Expected vs actual behavior
   - System information (OS, Java version)
   - Screenshots if applicable

### Feature Requests

1. Open an issue with `enhancement` label
2. Describe the feature and use case
3. Provide mockups or examples if possible

### Pull Requests

1. Fork the repository
2. Create a feature branch:
   ```bash
   git checkout -b feature/amazing-feature
   ```
3. Make your changes
4. Follow Java coding conventions
5. Add Javadoc comments
6. Test thoroughly
7. Commit with clear messages:
   ```bash
   git commit -m "Add: New weather alert feature"
   ```
8. Push to your fork:
   ```bash
   git push origin feature/amazing-feature
   ```
9. Open a Pull Request

### Code Style Guidelines

- Follow Java naming conventions
- Use meaningful variable names
- Add Javadoc for all public methods
- Keep methods focused and concise
- Maximum line length: 100 characters
- Use 4 spaces for indentation

---

## 📄 License

This project is licensed under the MIT License:

```
MIT License

Copyright (c) 2024 Michael Semera

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

---

## 📧 Contact

**Michael Semera**

- 💼 LinkedIn: [Michael Semera](https://www.linkedin.com/in/michael-semera-586737295/)
- 🐙 GitHub: [@MichaelKS123](https://github.com/MichaelKS123)
- 📧 Email: michaelsemera15@gmail.com

---

## 🙏 Acknowledgments

- **OpenWeatherMap** for providing the weather API
- **JavaFX community** for excellent documentation
- **Oracle** for Java development platform
- **Open source contributors** for libraries and tools

---

## 📚 Resources

### Documentation
- [JavaFX Documentation](https://openjfx.io/javadoc/17/)
- [OpenWeatherMap API Docs](https://openweathermap.org/api)
- [JSON-Java GitHub](https://github.com/stleary/JSON-java)

### Tutorials
- [JavaFX Tutorial - Oracle](https://docs.oracle.com/javafx/)
- [Charts in JavaFX](https://docs.oracle.com/javafx/2/charts/jfxpub-charts.htm)

### Tools
- [Scene Builder](https://gluonhq.com/products/scene-builder/)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/)
- [Maven Repository](https://mvnrepository.com/)

---

## ⭐ Show Your Support

If you find AtmosNow helpful:
- Star the repository ⭐
- Share with friends and colleagues
- Report issues or suggest features
- Contribute improvements

---

**Last Updated**: November 2024  
**Version**: 1.0.0  
**Status**: Active Development

---

*Built with ☕ and passion for clean code*