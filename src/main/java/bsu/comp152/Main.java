//Walrus image used is copyright free
// https://pngriver.com/download-walrus-png-free-download-for-designing-projects-41482/
package bsu.comp152;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Parent root = null;
        var loc = getClass().getResource("Main.fxml");
        try {
            root = FXMLLoader.load(loc);
        }catch (IOException e) {
            System.out.println("Could not find FXML file!!!");
        }
        Scene windowContents = new Scene(root, 600, 400); //sets scene
        primaryStage.setScene(windowContents); //sets the scene in the window
        primaryStage.setTitle("Wally's Weather Report"); //sets window title
        primaryStage.getIcons().add(new Image("https://pngriver.com/wp-content/uploads/2018/04/" +
                "Download-Walrus-PNG-Free-Download-For-Designing-Projects.png")); //sets app icon
        primaryStage.setResizable(false); //prevents resizing
        primaryStage.show(); //displays window
    }
}