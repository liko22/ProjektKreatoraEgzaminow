module KreatorEgzaminow {
    requires javafx.controls;
    requires javafx.fxml;

    opens KreatorEgzaminow to javafx.fxml;
    exports KreatorEgzaminow;
}