package edu.ib.projekt_punkt_szczepien;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ZleceniaSzczepienia {
    private StringProperty pesel;
    private StringProperty termin;
    private StringProperty typ_szczepionki;
    private IntegerProperty numer_zlecenia;

    public ZleceniaSzczepienia() {
        this.pesel = new SimpleStringProperty();
        this.termin = new SimpleStringProperty();
        this.typ_szczepionki = new SimpleStringProperty();
        this.numer_zlecenia = new SimpleIntegerProperty();
    }

    public int getNumer_zlecenia() {
        return numer_zlecenia.get();
    }

    public IntegerProperty numer_zleceniaProperty() {
        return numer_zlecenia;
    }

    public void setNumer_zlecenia(int numer_zlecenia) {
        this.numer_zlecenia.set(numer_zlecenia);
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

    public String getTermin() {
        return termin.get();
    }

    public StringProperty terminProperty() {
        return termin;
    }

    public void setTermin(String termin) {
        this.termin.set(termin);
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