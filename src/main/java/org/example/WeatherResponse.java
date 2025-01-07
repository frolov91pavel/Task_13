package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {
    @JsonProperty("current")
    private CurrentWeather current;

    public CurrentWeather getCurrent() {
        return current;
    }

    public void setCurrent(CurrentWeather current) {
        this.current = current;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CurrentWeather {
        @JsonProperty("temp_c")
        private double temperatureCelsius;

        @JsonProperty("condition")
        private WeatherCondition condition;

        public double getTemperatureCelsius() {
            return temperatureCelsius;
        }

        public void setTemperatureCelsius(double temperatureCelsius) {
            this.temperatureCelsius = temperatureCelsius;
        }

        public WeatherCondition getCondition() {
            return condition;
        }

        public void setCondition(WeatherCondition condition) {
            this.condition = condition;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class WeatherCondition {
        @JsonProperty("text")
        private String conditionText;

        public String getConditionText() {
            return conditionText;
        }

        public void setConditionText(String conditionText) {
            this.conditionText = conditionText;
        }
    }
}