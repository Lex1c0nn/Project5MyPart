package bsu.comp152;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.net.URL;
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
    private DataHandler handler;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        handler = new DataHandler();
    }

    public void loadData(ActionEvent event) {
        var site = "https://www.metaweather.com/api/";
        var city = location.getText().toLowerCase();  //need a try/catch here for bad user inputs

        if (city.length()< 1){
            location.deleteText(0, city.length());
            location.setPromptText("Please enter a city");
            return;
        }
        location.deleteText(0, city.length());
        location.setPromptText("Ex: Boston");

        var woeIDSite = site+ "location/search/?query="+ city;
        WoeIDData data = handler.getWoeID(woeIDSite);

        
        System.out.println(data);
        System.out.println(data.woeid); //we got em bois!
        //displayData(data);
    }
}
