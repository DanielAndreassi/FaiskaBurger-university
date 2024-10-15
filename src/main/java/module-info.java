module com.faiskaburgers.faiskaburger {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.faiskaburgers.faiskaburger to javafx.fxml;
    exports com.faiskaburgers.faiskaburger;
}