package edu.ib.projekt_punkt_szczepien;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArchiwumSzczepieniaDAO {

    private DBUtil dbUtil;
    private TextArea consoleTextArea;

    public ArchiwumSzczepieniaDAO(DBUtil dbUtil, TextArea consoleTextArea) {
        this.dbUtil = dbUtil;
        this.consoleTextArea = consoleTextArea;
    }

    private ObservableList<ArchiwumSzczepienia> getSzczepionkiArchiwumList(ResultSet rs) throws SQLException {

        ObservableList<ArchiwumSzczepienia> archiwumSzczepienias = FXCollections.observableArrayList();

        while (rs.next()) {

            edu.ib.projekt_punkt_szczepien.ArchiwumSzczepienia r = new edu.ib.projekt_punkt_szczepien.ArchiwumSzczepienia();
            r.setTyp_szczepionki(rs.getString("typ_szczepionki"));
            archiwumSzczepienias.add(r);
        }
        return archiwumSzczepienias;
    }

    private ObservableList<ArchiwumSzczepienia> getAmount(ResultSet rs) throws SQLException {

        ObservableList<ArchiwumSzczepienia> archiwumSzczepienias = FXCollections.observableArrayList();

        while (rs.next()) {

            ArchiwumSzczepienia r = new ArchiwumSzczepienia();
            r.setCount(rs.getInt("count(typ_szczepionki)"));
            archiwumSzczepienias.add(r);
        }
        return archiwumSzczepienias;
    }


    private ObservableList<ArchiwumSzczepienia> getArchiwumList(ResultSet rs) throws SQLException {

        ObservableList<ArchiwumSzczepienia> archiwumSzczepienias = FXCollections.observableArrayList();

        while (rs.next()) {

            ArchiwumSzczepienia r = new ArchiwumSzczepienia();
            r.setTyp_szczepionki(rs.getString("typ_szczepionki"));
            r.setRealizacja(rs.getString("realizacja"));
            r.setTermin(rs.getString("termin"));
            archiwumSzczepienias.add(r);
        }
        return archiwumSzczepienias;
    }

    public ObservableList<ArchiwumSzczepienia> searchArchiwum(String pesel) throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT termin, typ_szczepionki,realizacja FROM archiwum_szczepienia WHERE pesel like '" + pesel + "';";

        try {

            ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);

            ObservableList<ArchiwumSzczepienia> archiwum = getArchiwumList(resultSet);

            consoleTextArea.appendText(selectStmt + "\n");

            return archiwum;

        } catch (SQLException e) {
            consoleTextArea.appendText("While searching a patient who PESEL = '" + pesel + "' , an error occurred. \n");
            throw e;
        }

    }

    public ObservableList<String> selectedSzczepionki(String from, String to) throws SQLException, ClassNotFoundException {

        String selectStmt = "select typ_szczepionki from archiwum_szczepienia " +
                "where termin between '" + from + "' and '" + to + "' group by typ_szczepionki;";

        try {
            ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);
            ObservableList<ArchiwumSzczepienia> szczepionki = getSzczepionkiArchiwumList(resultSet);
            ObservableList<String> stringObservableList = FXCollections.observableArrayList();

            consoleTextArea.appendText(selectStmt + "\n");

            for (ArchiwumSzczepienia t : szczepionki) {
                stringObservableList.add(t.getTyp_szczepionki());
            }
            System.out.println(stringObservableList);
            return stringObservableList;

        } catch (SQLException e) {
            consoleTextArea.appendText("While searching deadlines, an error occurred. \n");
            throw e;
        }
    }

    public ObservableList<Integer> selectedAmount(String from, String to) throws SQLException, ClassNotFoundException {

        String selectStmt = "select count(typ_szczepionki) from archiwum_szczepienia " +
                "where termin between '" + from + "' and '" + to + "' group by typ_szczepionki;";

        try {
            ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);
            ObservableList<ArchiwumSzczepienia> szczepionki = getAmount(resultSet);
            ObservableList<Integer> stringObservableList = FXCollections.observableArrayList();

            consoleTextArea.appendText(selectStmt + "\n");

            for (ArchiwumSzczepienia t : szczepionki) {
                stringObservableList.add(t.getCount());
            }

            System.out.println(stringObservableList);
            return stringObservableList;

        } catch (SQLException e) {
            consoleTextArea.appendText("While searching deadlines, an error occurred. \n");
            throw e;
        }
    }
}
