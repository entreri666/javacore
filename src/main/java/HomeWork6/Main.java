package HomeWork6;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://dataservice.accuweather.com/forecasts/v1/daily/5day/294021" +
                        "?apikey=NIEReAJzPA86my4GDj9kFu2bjA9tw7vQ&language=ru-ru&metric=true")
                .build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }


        String weatherResponse = null;
        try {
            weatherResponse = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(weatherResponse);
    }
}
