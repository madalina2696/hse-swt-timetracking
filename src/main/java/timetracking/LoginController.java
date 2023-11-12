package timetracking;

import java.io.IOException;
import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private Label lblWrongLogin;

    @FXML
    private PasswordField pwfPassword;

    @FXML
    private TextField tfUsername;

    @FXML
    void btnLoginClicked(ActionEvent event) throws IOException {
        checkLogin();
    }

    private void checkLogin() throws IOException {
        String[][] loginData = CsvLoader.load("loginData");
        final HashMap<String, String[]> map = new HashMap<>(loginData.length);
        for (String[] mapping : loginData) {
            map.put(mapping[0], new String[] { mapping[1], mapping[2] });
        }
        try {
            if (map.get(tfUsername.getText())[0].equals(pwfPassword.getText())) {
                lblWrongLogin.setText("Login Succeeded!");
                CsvLoader.save("LastLogIn", new String[][] { { tfUsername.getText(), "" } });
                if (map.get(tfUsername.getText())[1].equals("e")) {
                    App.changeScene("employee.fxml");
                } else if (map.get(tfUsername.getText())[1].equals("s")) {
                    App.changeScene("supervisor.fxml");
                }
            }
        } catch (Exception e) {
            lblWrongLogin.setText("Wrong username or password. Please retry.");
            lblWrongLogin.setStyle("-fx-text-fill: red;");
        }
        if (tfUsername.getText().isEmpty() && pwfPassword.getText().isEmpty()) {
            lblWrongLogin.setText("Please enter your Login data.");
        }
    }

}
