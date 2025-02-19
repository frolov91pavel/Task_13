import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.example.WeatherApp;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.IOException;

class WeatherAppTest {

    private static MockWebServer mockWebServer;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    static void setup() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void testGetWeather_Success() throws IOException {

        String jsonResponse = """
            {
                "current": {
                    "temp_c": 25.5,
                    "condition": { "text": "Солнечно" }
                }
            }
        """;

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(jsonResponse));

        String testUrl = mockWebServer.url("/").toString();
        WeatherApp weatherApp = new WeatherApp(testUrl);

        String result = weatherApp.getWeather("Moscow");

        Assertions.assertEquals("Температура: 25,5°C, Облачность: Солнечно", result);
    }

    @Test
    void testGetWeather_Fail() {
        mockWebServer.enqueue(new MockResponse().setResponseCode(404));

        WeatherApp weatherApp = new WeatherApp(mockWebServer.url("/").toString());

        Exception exception = Assertions.assertThrows(IOException.class, () -> {
            weatherApp.getWeather("UnknownCity");
        });

        Assertions.assertTrue(exception.getMessage().contains("Не удалось получить данные: Response{protocol=http/1.1, code=404"));
    }
}