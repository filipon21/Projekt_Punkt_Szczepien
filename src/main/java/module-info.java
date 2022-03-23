module edu.ib.projekt_punkt_szczepien {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql.rowset;


    opens edu.ib.projekt_punkt_szczepien to javafx.fxml;
    exports edu.ib.projekt_punkt_szczepien;
}