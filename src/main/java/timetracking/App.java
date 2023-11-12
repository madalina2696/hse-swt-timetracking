package timetracking;

import java.io.IOException;
import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    private static Stage stg;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root;
        stg = primaryStage;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml")));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Login");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void changeScene(String fxml) {
        stg.hide();
        Parent pane;
        try {
            pane = FXMLLoader.load(App.class.getResource(fxml));
            Scene scene = new Scene(pane);
            stg.setTitle("Arbeitszeiterfassung");
            stg.setScene(scene);
            stg.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}