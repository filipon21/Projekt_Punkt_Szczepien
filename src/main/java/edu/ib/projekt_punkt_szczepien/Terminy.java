package edu.ib.projekt_punkt_szczepien;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Terminy {
    private StringProperty termin;

    public Terminy() {
        this.termin = new SimpleStringProperty();
    }

    public String getTermin() {
        return termin.get();
    }

    public StringProperty terminProperty() {
        return termin;
    }

    public void setTermin(String termin) {
        this.termin.set(termin);
    }
}
