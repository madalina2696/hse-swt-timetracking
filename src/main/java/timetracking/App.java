package timetracking;

import java.io.IOException;

import javafx.application.Application;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {
    private static Stage stg;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root;
        stg = primaryStage;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginScene.fxml")));
            Scene scene = new Scene(root);

            primaryStage.setTitle("Login");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public void changeScene(String fxml) {
        stg.hide();

        Parent pane;
        try {
            pane = FXMLLoader.load(getClass().getResource(fxml));
            Scene scene = new Scene(pane);
            stg.setTitle("Arbeitszeiterfassung");
            stg.setScene(scene);
            stg.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}