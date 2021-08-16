package HomeWork7;

import HomeWork7.entity.Weather;

import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


    public class DataBaseRepository {
        private String insertWeather = "insert into weather (city, localdate, temperature) values (?, ?, ?)";
        private String getWeather = "select * from weather";
        private static final String DB_PATH = "jdbc:sqlite:Accuweather.db";

        static {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        public boolean saveWeatherToDataBase(Weather weather) throws SQLException {
            try (Connection connection = DriverManager.getConnection(DB_PATH)) {
                PreparedStatement saveWeather = connection.prepareStatement(insertWeather);
                saveWeather.setString(1, weather.getCity());
                saveWeather.setString(2, weather.getLocalDate());
                saveWeather.setDouble(3, weather.getTemperature());
                return saveWeather.execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            throw new SQLException("Сохранение погоды в базу данных не выполнено!");
        }

        public void saveWeatherToDataBase(List<Weather> weatherList) throws SQLException {
            try (Connection connection = DriverManager.getConnection(DB_PATH)) {
                PreparedStatement saveWeather = connection.prepareStatement(insertWeather);
                for (Weather weather : weatherList) {
                    saveWeather.setString(1, weather.getCity());
                    saveWeather.setString(2, weather.getLocalDate());
                    saveWeather.setDouble(3, weather.getTemperature());
                    saveWeather.addBatch();
                }
                saveWeather.executeBatch();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        //public List<Weather> getSavedToDBWeather() {
        //    try (Connection connection = DriverManager.getConnection(DB_PATH)) {
        //        //TODO: реализовать этот метод получения данных из таблицы погоды
        //    } catch (SQLException throwables) {
        //        throwables.printStackTrace();
        //    }
        //}

        public List<Weather> getSavedToDBWeather() {
            List<Weather> weathers = new ArrayList<>();
            try (Connection connection = DriverManager.getConnection(DB_PATH)) {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(getWeather);
                while (resultSet.next()) {
                    System.out.print(resultSet.getInt("id"));
                    System.out.println(" ");
                    System.out.print(resultSet.getString("city"));
                    System.out.println(" ");
                    System.out.print(resultSet.getString("localdate"));
                    System.out.println(" ");
                    System.out.print(resultSet.getDouble("temperature"));
                    System.out.println(" ");
                    weathers.add(new Weather(resultSet.getString("city"),
                            resultSet.getString("localdate"),
                            resultSet.getDouble("temperature")));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return weathers;
        }

        public static void main(String[] args) throws SQLException {
            DataBaseRepository dataBaseRepository = new DataBaseRepository();
            dataBaseRepository.saveWeatherToDataBase(new Weather("Москва", "09.08.21", 28));

            dataBaseRepository.saveWeatherToDataBase(new ArrayList<>(Arrays.asList(
                    new Weather("Москва", "09.08.21", 28),
                    new Weather("Красноярск", "07.08.21", 25),
                    new Weather("Омск", "05.06.21", 32),
                    new Weather("Сочи", "09.09.21", 31),
                    new Weather("Самара", "10.08.21", 29),
                    new Weather("Москва", "12.08.21", 35))));
            System.out.println(dataBaseRepository.getSavedToDBWeather());
        }
    }

