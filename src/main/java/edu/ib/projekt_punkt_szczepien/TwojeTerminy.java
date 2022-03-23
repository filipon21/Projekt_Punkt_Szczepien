package edu.ib.projekt_punkt_szczepien;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TwojeTerminy {
    private StringProperty typ_szczepionki;
    private StringProperty termin;

    public TwojeTerminy() {
        this.typ_szczepionki = new SimpleStringProperty();
        this.termin = new SimpleStringProperty();
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
