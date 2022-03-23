package edu.ib.projekt_punkt_szczepien;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MozliwoscSzczepienia {
    private StringProperty pesel;
    private StringProperty czy_moze_szczepic;
    private StringProperty typ_szczepionki;

    public MozliwoscSzczepienia() {
        this.pesel = new SimpleStringProperty();
        this.czy_moze_szczepic = new SimpleStringProperty();
        this.typ_szczepionki = new SimpleStringProperty();
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

    public String getPesel() {
        return pesel.get();
    }

    public StringProperty peselProperty() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel.set(pesel);
    }

    public String getCzy_moze_szczepic() {
        return czy_moze_szczepic.get();
    }

    public StringProperty czy_moze_szczepicProperty() {
        return czy_moze_szczepic;
    }

    public void setCzy_moze_szczepic(String czy_moze_szczepic) {
        this.czy_moze_szczepic.set(czy_moze_szczepic);
    }
}
