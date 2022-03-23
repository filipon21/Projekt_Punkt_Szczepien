package edu.ib.projekt_punkt_szczepien;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PacjentDAO {
    private DBUtil dbUtil;
    private TextArea consoleTextArea;

    public PacjentDAO(DBUtil dbUtil, TextArea consoleTextArea) {
        this.dbUtil = dbUtil;
        this.consoleTextArea = consoleTextArea;
    }

    private ObservableList<ArchiwumSzczepienia> getArchiwumSzczepieniaList(ResultSet rs) throws SQLException {

        ObservableList<ArchiwumSzczepienia>  archiwumSzczepienias= FXCollections.observableArrayList();

        while (rs.next()) {

            ArchiwumSzczepienia r = new ArchiwumSzczepienia();
            r.setPesel(rs.getString("pesel"));
            r.setRealizacja(rs.getString("realizacja"));
            r.setTermin(rs.getString("termin"));
            r.setTyp_szczepionki(rs.getString("typ_szczepionki"));
            archiwumSzczepienias.add(r);
        }


        return archiwumSzczepienias;
    }

    public void insertPacjent(String imie, String nazwisko, String pesel, String numer_telefonu, String data_urodzenia)
            throws SQLException, ClassNotFoundException {

        StringBuilder sb = new StringBuilder("INSERT INTO pacjenci(imie, nazwisko, pesel, numer_telefonu, data_urodzenia) VALUES('");
        sb.append(imie);
        sb.append("','");
        sb.append(nazwisko);
        sb.append("','");
        sb.append(pesel);
        sb.append("','");
        sb.append(numer_telefonu);
        sb.append("','");
        sb.append(data_urodzenia);
        sb.append("');");
        String insertStmt = sb.toString();

        try {

            dbUtil.dbExecuteUpdate(insertStmt);
            consoleTextArea.appendText(insertStmt + "\n");

        } catch (SQLException e) {
            consoleTextArea.appendText("Error occurred while INSERT Operation.");
            throw e;
        }

    }
}
