module timetracking {
    requires javafx.controls;
    requires javafx.fxml;

    opens timetracking to javafx.fxml;
    exports timetracking;
}
