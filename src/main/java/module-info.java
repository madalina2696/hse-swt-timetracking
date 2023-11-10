module timetracking {
    requires javafx.controls;
    requires javafx.base;

    opens timetracking to javafx.fxml;
    exports timetracking;
}
