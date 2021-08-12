package HomeWork7;

import java.io.IOException;
import HomeWork7.entity.Weather;
import java.util.List;

public interface WeatherModel {
    void getWeather(String selectedCity, Period period) throws IOException;

    public List<Weather> getSavedToDBWeather();
}
