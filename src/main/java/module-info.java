module swt {
    requires javafx.controls;
    requires javafx.fxml;

    opens swt to javafx.fxml;
    exports swt;
}
