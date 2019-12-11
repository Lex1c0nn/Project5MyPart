package bsu.comp152;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Controller implements Initializable{
    @FXML
    public RadioButton humidity, wind, airPressure, visibility;
    public TextField location;
    public ImageView imageD0, imageD1, imageD2, imageD3, imageD4;
    public Label tempD0, tempD1, tempD2, tempD3, tempD4,
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
