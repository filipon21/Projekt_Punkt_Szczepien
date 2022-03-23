package edu.ib.projekt_punkt_szczepien;
import javafx.beans.property.*;

public class Pacjent {
    private StringProperty pesel;
    private StringProperty imie;
    private StringProperty nazwisko;
    private StringProperty numer_telefonu;
    private StringProperty data_urodzenia;

    public Pacjent() {
        this.pesel = new SimpleStringProperty();
        this.imie = new SimpleStringProperty();
        this.nazwisko = new SimpleStringProperty();
        this.numer_telefonu = new SimpleStringProperty();
        this.data_urodzenia = new SimpleStringProperty();
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

    public String getImie() {
        return imie.get();
    }

    public StringProperty imieProperty() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie.set(imie);
    }

    public String getNazwisko() {
        return nazwisko.get();
    }

    public StringProperty nazwiskoProperty() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko.set(nazwisko);
    }

    public String getNumer_telefonu() {
        return numer_telefonu.get();
    }

    public StringProperty numer_telefonuProperty() {
        return numer_telefonu;
    }

    public void setNumer_telefonu(String numer_telefonu) {
        this.numer_telefonu.set(numer_telefonu);
    }

    public String getData_urodzenia() {
        return data_urodzenia.get();
    }

    public StringProperty data_urodzeniaProperty() {
        return data_urodzenia;
    }

    public void setData_urodzenia(String data_urodzenia) {
        this.data_urodzenia.set(data_urodzenia);
    }
}
