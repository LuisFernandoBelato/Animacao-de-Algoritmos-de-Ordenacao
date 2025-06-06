module com.example.combsortanimacaopo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.combsortanimacaopo to javafx.fxml;
    exports com.example.combsortanimacaopo;
}