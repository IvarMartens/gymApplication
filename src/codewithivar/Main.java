package codewithivar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FXML/home.fxml"));
        primaryStage.setTitle("Gym application");

        FileInputStream inputStream = new FileInputStream("dumbell.png");
        Image windowIcon = new Image(inputStream);
        primaryStage.getIcons().add(windowIcon);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
