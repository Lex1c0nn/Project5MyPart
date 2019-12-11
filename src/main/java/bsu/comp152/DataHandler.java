package bsu.comp152;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class DataHandler {
    private HttpClient dataGrabber;


    public DataHandler() {
        dataGrabber = HttpClient.newHttpClient();
    }

    public WoeIDData getWoeID(String webLocation) {
        var requestBuilder = HttpRequest.newBuilder();
        var dataRequest = requestBuilder.uri(URI.create(webLocation)).build();
        HttpResponse<String> response = null;
        try {
            response = dataGrabber.send(dataRequest, HttpResponse.BodyHandlers.ofString());
        }catch(IOException e){
            System.out.println("Error connecting to network or site");
        }
        catch (InterruptedException e){
            System.out.println("Connection to site broken");
        }
        if (response == null ){
            System.out.println("Something went terribly wrong, ending program");
            System.exit(-1);
        }

        var usefulData = response.body();
        usefulData = usefulData.substring(1,usefulData.length()-1); //THANK YOU PROFESSOR <3

        var jsonInterpreter = new Gson();
        var cityData = jsonInterpreter.fromJson(usefulData, WoeIDData.class);
        return cityData;
    }

    public String getWeather(String webLocation) {
        var requestBuilder = HttpRequest.newBuilder();
        var dataRequest = requestBuilder.uri(URI.create(webLocation)).build();
        HttpResponse<String> response = null;
        try {
            response = dataGrabber.send(dataRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            System.out.println("Error connecting to network or site");
        } catch (InterruptedException e) {
            System.out.println("Connection to site broken");
        }
        if (response == null) {
            System.out.println("Something went terribly wrong, ending program");
            System.exit(-1);
        }

        var weatherResponse = response.body();
        return weatherResponse;
    }

    public String[] dailyWeather(String fullWeather) {
        String weatherList[] = fullWeather.split("}",6);
        return weatherList;
    }

    public WeatherData0 grabWeatherData0(String wotd){
        var todaysWeather = wotd.substring(25);
        todaysWeather = todaysWeather + "}";

        var jsonInterpreter = new Gson();
        var weatherData = jsonInterpreter.fromJson(todaysWeather, WeatherData0.class);
        return weatherData;
    }

    public WeatherData1 grabWeatherData1(String wotd){
        var todaysWeather = wotd.substring(1);
        todaysWeather = todaysWeather + "}";

        var jsonInterpreter = new Gson();
        var weatherData = jsonInterpreter.fromJson(todaysWeather, WeatherData1.class);
        return weatherData;
    }

    public WeatherData2 grabWeatherData2(String wotd){
        var todaysWeather = wotd.substring(1);
        todaysWeather = todaysWeather + "}";

        var jsonInterpreter = new Gson();
        var weatherData = jsonInterpreter.fromJson(todaysWeather, WeatherData2.class);
        return weatherData;
    }

    public WeatherData3 grabWeatherData3(String wotd){
        var todaysWeather = wotd.substring(1);
        todaysWeather = todaysWeather + "}";

        var jsonInterpreter = new Gson();
        var weatherData = jsonInterpreter.fromJson(todaysWeather, WeatherData3.class);
        return weatherData;
    }

    public WeatherData4 grabWeatherData4(String wotd){
        var todaysWeather = wotd.substring(1);
        todaysWeather = todaysWeather + "}";

        var jsonInterpreter = new Gson();
        var weatherData = jsonInterpreter.fromJson(todaysWeather, WeatherData4.class);
        return weatherData;
    }
}

class WoeIDData{
    String title;
    String location_type;
    Integer woeid;
    String latt_long;
}

class WeatherData0{
    Double id;
    String weather_state_name;
    String weather_state_abbr;
    String wind_direction_compass;
    String created;
    String applicable_date;
    Float min_temp;
    Float max_temp;
    Float the_temp;
    Float wind_speed;
    Float wind_direction;
    Float air_pressure;
    Float humidity;
    Float visibility;
    Integer predictability;
}

class WeatherData1{
    Double id;
    String weather_state_name;
    String weather_state_abbr;
    String wind_direction_compass;
    String created;
    String applicable_date;
    Float min_temp;
    Float max_temp;
    Float the_temp;
    Float wind_speed;
    Float wind_direction;
    Float air_pressure;
    Float humidity;
    Float visibility;
    Integer predictability;
}

class WeatherData2{
    Double id;
    String weather_state_name;
    String weather_state_abbr;
    String wind_direction_compass;
    String created;
    String applicable_date;
    Float min_temp;
    Float max_temp;
    Float the_temp;
    Float wind_speed;
    Float wind_direction;
    Float air_pressure;
    Float humidity;
    Float visibility;
    Integer predictability;
}

class WeatherData3{
    Double id;
    String weather_state_name;
    String weather_state_abbr;
    String wind_direction_compass;
    String created;
    String applicable_date;
    Float min_temp;
    Float max_temp;
    Float the_temp;
    Float wind_speed;
    Float wind_direction;
    Float air_pressure;
    Float humidity;
    Float visibility;
    Integer predictability;
}

class WeatherData4{
    Double id;
    String weather_state_name;
    String weather_state_abbr;
    String wind_direction_compass;
    String created;
    String applicable_date;
    Float min_temp;
    Float max_temp;
    Float the_temp;
    Float wind_speed;
    Float wind_direction;
    Float air_pressure;
    Float humidity;
    Float visibility;
    Integer predictability;
}

