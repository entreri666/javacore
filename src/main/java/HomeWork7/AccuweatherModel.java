package HomeWork7;

import HomeWork7.entity.Weather;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

public class AccuweatherModel implements WeatherModel {
    //http://dataservice.accuweather.com/forecasts/v1/daily/1day/349727
    private static final String PROTOKOL = "https";
    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECASTS = "forecasts";
    private static final String VERSION = "v1";
    private static final String DAILY = "daily";
    private static final String ONE_DAY = "1day";
    private static final String FIVE_DAYS = "5day";
    private static final String API_KEY = "NIEReAJzPA86my4GDj9kFu2bjA9tw7vQ";
    private static final String API_KEY_QUERY_PARAM = "apikey";
    private static final String LOCATIONS = "locations";
    private static final String CITIES = "cities";
    private static final String AUTOCOMPLETE = "autocomplete";

    private static final OkHttpClient okHttpClient = new OkHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private DataBaseRepository dataBaseRepository = new DataBaseRepository();

    public void getWeather(String selectedCity, Period period) throws IOException {
        switch (period) {
            case NOW:
                HttpUrl httpUrlOneDay = new HttpUrl.Builder()
                        .scheme(PROTOKOL)
                        .host(BASE_HOST)
                        .addPathSegment(FORECASTS)
                        .addPathSegment(VERSION)
                        .addPathSegment(DAILY)
                        .addPathSegment(ONE_DAY)
                        .addPathSegment(detectCityKey(selectedCity))
                        .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                        .build();

                Request requestOneDay = new Request.Builder()
                        .url(httpUrlOneDay)
                        .build();

                Response oneDayForecastResponse = okHttpClient.newCall(requestOneDay).execute();
                String weatherResponseOneDay = oneDayForecastResponse.body().string();
                //TODO: ?????????????? ???????????????????????????????? ?????????? ????????????. ?????????????? ?????????????????? ?????? ???????????? ???? ???????? ????????????????????
                //????????????????: ???????????? ?? ???????????? ???????????? - 5 ???????????????? ???? ?????????????? Expect showers late Monday night
                //dataBaseRepository.saveWeatherToDataBase(new Weather()) - ?????? ?????????? ???????????????? ?????????????????? ???????????? ?? ????
                String temperatureMinimumValue = objectMapper.readTree(weatherResponseOneDay)
                        .get("DailyForecasts").get(0).get("Temperature").get("Minimum").at("/Value").asText();
                String temperatureMaximumValue = objectMapper.readTree(weatherResponseOneDay)
                        .get("DailyForecasts").get(0).get("Temperature").get("Maximum").at("/Value").asText();
                String temperatureUnit = objectMapper.readTree(weatherResponseOneDay)
                        .get("DailyForecasts").get(0).get("Temperature").get("Minimum").at("/Unit").asText();
                String dayCloudy = objectMapper.readTree(weatherResponseOneDay)
                        .get("DailyForecasts").get(0).get("Day").at("/IconPhrase").asText();
                String nightCloudy = objectMapper.readTree(weatherResponseOneDay)
                        .get("DailyForecasts").get(0).get("Night").at("/IconPhrase").asText();
                String weatherDescription = objectMapper.readTree(weatherResponseOneDay)
                        .get("Headline").at("/Text").asText();
                weatherResponseOneDay = ("Temperature from: " + " " + temperatureMinimumValue + " " + temperatureUnit
                        + " Temperature up to: " + temperatureMaximumValue + " " + temperatureUnit + "\n"
                        + "Cloudy day: " + dayCloudy + "\n" + "Cloudy night: " + nightCloudy + "\n" + "Description: "
                        + weatherDescription);
                System.out.println(weatherResponseOneDay);
                break;
            case FIVE_DAYS:
                //TODO*: ?????????????????????? ?????????? ???????????? ???? 5 ????????
                HttpUrl httpUrlFiveDays = new HttpUrl.Builder()
                        .scheme(PROTOKOL)
                        .host(BASE_HOST)
                        .addPathSegment(FORECASTS)
                        .addPathSegment(VERSION)
                        .addPathSegment(DAILY)
                        .addPathSegment(FIVE_DAYS)
                        .addPathSegment(detectCityKey(selectedCity))
                        .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                        .build();

                Request requestFiveDays = new Request.Builder()
                        .url(httpUrlFiveDays)
                        .build();

                Response fiveDaysForecastResponse = okHttpClient.newCall(requestFiveDays).execute();
                String weatherResponseFiveDays = fiveDaysForecastResponse.body().string();
                System.out.println(weatherResponseFiveDays);
                break;
        }
    }

    @Override
    public List<Weather> getSavedToDBWeather() {
        return dataBaseRepository.getSavedToDBWeather();

    }

    private static String detectCityKey(String selectCity) throws IOException {
        //http://dataservice.accuweather.com/locations/v1/cities/autocomplete
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(PROTOKOL)
                .host(BASE_HOST)
                .addPathSegment(LOCATIONS)
                .addPathSegment(VERSION)
                .addPathSegment(CITIES)
                .addPathSegment(AUTOCOMPLETE)
                .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                .addQueryParameter("q", selectCity)
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .get()
                .addHeader("accept", "application/json")
                .build();

        Response response = okHttpClient.newCall(request).execute();
        String responseString = response.body().string();

        String cityKey = objectMapper.readTree(responseString).get(0).at("/Key").asText();
        return cityKey;
    }
}