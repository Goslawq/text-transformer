module pl.put.poznan {
    requires javafx.controls;
    requires javafx.fxml;

    opens pl.put.poznan to javafx.fxml;
    exports pl.put.poznan;
}