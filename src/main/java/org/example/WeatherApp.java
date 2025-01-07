package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Scanner;

public class WeatherApp {
    private static final String API_KEY = "a57c83748d30441faf5143227241312";
    private static final String BASE_URL = "https://api.weatherapi.com/v1/current.json";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите название города: ");
        String city = scanner.nextLine();

        try {
            String weatherInfo = getWeather(city);
            System.out.println(weatherInfo);
        } catch (IOException e) {
            System.err.println("Ошибка при получении данных: " + e.getMessage());
        }
    }

    private static String getWeather(String city) throws IOException {
        OkHttpClient client = new OkHttpClient();

        String url = BASE_URL + "?key=" + API_KEY + "&q=" + city + "&aqi=no";
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