package edu.ib.projekt_punkt_szczepien;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Szczepionki {
    private IntegerProperty id_szczepionki;
    private StringProperty typ_szczepionki;

    public Szczepionki() {
        this.id_szczepionki = new SimpleIntegerProperty();
        this.typ_szczepionki = new SimpleStringProperty();
    }

    public int getId_szczepionki() {
        return id_szczepionki.get();
    }

    public IntegerProperty id_szczepionkiProperty() {
        return id_szczepionki;
    }

    public void setId_szczepionki(int id_szczepionki) {
        this.id_szczepionki.set(id_szczepionki);
    }

    public String getTyp_szczepionki() {
        return typ_szczepionki.get();
    }

    public StringProperty typ_szczepionkiProperty() {
        return typ_szczepionki;
    }

    public void setTyp_szczepionki(String typ_szczepionki) {
        this.typ_szczepionki.set(typ_szczepionki);
    }
}
