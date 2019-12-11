package bsu.comp152;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Controller implements Initializable{
    @FXML
    public RadioButton humidity, wind, airPressure, visibility;
    public TextField location;
    public ImageView imageD0, imageD1, imageD2, imageD3, imageD4;
    public Label date0, date1, date2, date3, date4,
            tempD0, tempD1, tempD2, tempD3, tempD4,
            humidD0, humidD1, humidD2, humidD3, humidD4,
            windD0, windD1, windD2, windD3, windD4,
            airPressD0, airPressD1, airPressD2, airPressD3, airPressD4,
            visD0, visD1, visD2, visD3, visD4;
    public MenuButton scaleSelect;
    public MenuItem centigrade, fahrenheit, kelvin;
    private DataHandler woeidHandler;
    private DataHandler weatherListHandler;
    private ArrayList<RadioButton> condButtons;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        woeidHandler = new DataHandler();
        weatherListHandler = new DataHandler();

        condButtons = new ArrayList<RadioButton>();
        condButtons.add(humidity);
        condButtons.add(wind);
        condButtons.add(airPressure);
        condButtons.add(visibility);
    }

    public void loadData(ActionEvent event) {
        var site = "https://www.metaweather.com/api/";
        var city = location.getText().toLowerCase();
        var newCity = city.replace(" ","%20");
        System.out.println(city);

        if (city.length()< 1){
            location.deleteText(0, city.length());
            location.setPromptText("Please enter a city");
            return;
        }
        location.deleteText(0, city.length());
        location.setPromptText("Ex: Boston");

        var woeIDSite = site+ "location/search/?query="+ newCity;
        WoeIDData idData = woeidHandler.getWoeID(woeIDSite);

        var weatherSite = site+ "location/"+ idData.woeid+ "/";
        WeatherData0 w0Data = weatherListHandler.grabWeatherData0(weatherListHandler.dailyWeather(
                weatherListHandler.getWeather(weatherSite))[0]);
        WeatherData1 w1Data = weatherListHandler.grabWeatherData1(weatherListHandler.dailyWeather(
                weatherListHandler.getWeather(weatherSite))[1]);
        WeatherData2 w2Data = weatherListHandler.grabWeatherData2(weatherListHandler.dailyWeather(
                weatherListHandler.getWeather(weatherSite))[2]);
        WeatherData3 w3Data = weatherListHandler.grabWeatherData3(weatherListHandler.dailyWeather(
                weatherListHandler.getWeather(weatherSite))[3]);
        WeatherData4 w4Data = weatherListHandler.grabWeatherData4(weatherListHandler.dailyWeather(
                weatherListHandler.getWeather(weatherSite))[4]);

        date0.setText(w0Data.applicable_date);
        date1.setText(w1Data.applicable_date);
        date2.setText(w2Data.applicable_date);
        date3.setText(w3Data.applicable_date);
        date4.setText(w4Data.applicable_date);

        String basePath = "https://www.metaweather.com/static/img/weather/png/";

        Image image0 = new Image(basePath+ w0Data.weather_state_abbr+ ".png");
        imageD0.setImage(image0);
        Image image1 = new Image(basePath+ w1Data.weather_state_abbr+ ".png");
        imageD1.setImage(image1);
        Image image2 = new Image(basePath+ w2Data.weather_state_abbr+ ".png");
        imageD2.setImage(image2);
        Image image3 = new Image(basePath+ w3Data.weather_state_abbr+ ".png");
        imageD3.setImage(image3);
        Image image4 = new Image(basePath+ w4Data.weather_state_abbr+ ".png");
        imageD4.setImage(image4);

        DecimalFormat df = new DecimalFormat("###.##");
        switch (scaleSelect.getText()){
            case "Centigrade":
                tempD0.setText(df.format(w0Data.the_temp)+ " \u00B0C");
                tempD1.setText(df.format(w1Data.the_temp)+ " \u00B0C");
                tempD2.setText(df.format(w2Data.the_temp)+ " \u00B0C");
                tempD3.setText(df.format(w3Data.the_temp)+ " \u00B0C");
                tempD4.setText(df.format(w4Data.the_temp)+ " \u00B0C");
                break;
            case "Fahrenheit":
                var tempD0F = (((w0Data.the_temp* (9/5))+ 32));
                var tempD1F = (((w1Data.the_temp* (9/5))+ 32));
                var tempD2F = (((w2Data.the_temp* (9/5))+ 32));
                var tempD3F = (((w3Data.the_temp* (9/5))+ 32));
                var tempD4F = (((w4Data.the_temp* (9/5))+ 32));
                tempD0.setText(df.format(tempD0F)+ " \u00B0F");
                tempD1.setText(df.format(tempD1F)+ " \u00B0F");
                tempD2.setText(df.format(tempD2F)+ " \u00B0F");
                tempD3.setText(df.format(tempD3F)+ " \u00B0F");
                tempD4.setText(df.format(tempD4F)+ " \u00B0F");
                break;
            case "Kelvin":
                var tempD0K = (w0Data.the_temp+ 273);
                var tempD1K = (w1Data.the_temp+ 273);
                var tempD2K = (w2Data.the_temp+ 273);
                var tempD3K = (w3Data.the_temp+ 273);
                var tempD4K = (w4Data.the_temp+ 273);
                tempD0.setText(df.format(tempD0K)+ " \u00B0K");
                tempD1.setText(df.format(tempD1K)+ " \u00B0K");
                tempD2.setText(df.format(tempD2K)+ " \u00B0K");
                tempD3.setText(df.format(tempD3K)+ " \u00B0K");
                tempD4.setText(df.format(tempD4K)+ " \u00B0K");
                break;
        }
    }

    private ArrayList<String> getRequestedData(){
        ArrayList<String> selectedData = new ArrayList<>();
        for(var button: condButtons){
            if(button.isSelected())
                selectedData.add(button.getText());
        }
        return selectedData;
    }

    @FXML
    private void setScaleSelect(javafx.event.ActionEvent actionEvent){
        var scale = (MenuItem)actionEvent.getSource();
        scaleSelect.setText(scale.getText());
    }
}
