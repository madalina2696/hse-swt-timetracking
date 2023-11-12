package swt;

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
        stg = primaryStage;
        primaryStage.setTitle("Login");
        Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/swt/Login.fxml"))));
        primaryStage.setScene(scene);
        primaryStage.show();
        /*
         * try {
         * root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(
         * "LoginScene.fxml")));
         * Scene scene = new Scene(root);
         * primaryStage.setTitle("Login");
         * primaryStage.setScene(scene);
         * primaryStage.show();
         * } catch (IOException e) {
         * e.printStackTrace();
         * }
         */
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
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}