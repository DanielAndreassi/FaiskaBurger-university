module com.faiskaburgers.faiskaburger {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.faiskaburgers.faiskaburger to javafx.fxml;
    opens com.faiskaburgers.faiskaburger.database.entity to javafx.fxml;
    exports com.faiskaburgers.faiskaburger;
    exports com.faiskaburgers.faiskaburger.database.entity;

}