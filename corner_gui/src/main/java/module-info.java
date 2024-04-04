module corner_gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;

    opens corner_gui to javafx.fxml;
    exports corner_gui;

    opens model to javafx.fxml;
    exports model;
}
