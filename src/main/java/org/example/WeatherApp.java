package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Scanner;

public class WeatherApp {
    private static final String DEFAULT_API_KEY = "a57c83748d30441faf5143227241312";
    private final String baseUrl;

    public WeatherApp() {
        this.baseUrl = "https://api.weatherapi.com/v1/current.json";
    }

    // Конструктор для тестов (позволяет подставить mock-сервер)
    public WeatherApp(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getWeather(String city) throws IOException {
        OkHttpClient client = new OkHttpClient();

        String url = baseUrl + "?key=" + DEFAULT_API_KEY + "&q=" + city + "&aqi=no";
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Не удалось получить данные: " + response);
            }

            ObjectMapper mapper = new ObjectMapper();
            WeatherResponse weatherResponse = mapper.readValue(response.body().string(), WeatherResponse.class);

            double temperature = weatherResponse.getCurrent().getTemperatureCelsius();
            String condition = weatherResponse.getCurrent().getCondition().getConditionText();

            return String.format("Температура: %.1f°C, Облачность: %s", temperature, condition);
        }
    }
}