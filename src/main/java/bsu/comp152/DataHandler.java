package bsu.comp152;

import com.google.gson.Gson;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;


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
        System.out.println("handler"+ response);
        return cityData;
    }
}

class WoeIDData{
    String title;
    String location_type;
    Integer woeid;
    String latt_long;
}