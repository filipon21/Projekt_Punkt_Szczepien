package edu.ib.projekt_punkt_szczepien;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SzczepionkiDAO {
    private DBUtil dbUtil;
    private TextArea consoleTextArea;

    public SzczepionkiDAO(DBUtil dbUtil, TextArea consoleTextArea) {
        this.dbUtil = dbUtil;
        this.consoleTextArea = consoleTextArea;
    }

    private ObservableList<Szczepionki> getAllFromSzczepionki(ResultSet rs) throws SQLException {

        ObservableList<Szczepionki> szczepionki = FXCollections.observableArrayList();

        while (rs.next()) {

            Szczepionki s = new Szczepionki();
            s.setId_szczepionki(rs.getInt("id_szczepionki"));
            s.setTyp_szczepionki(rs.getString("typ_szczepionki"));
            szczepionki.add(s);
        }
        return szczepionki;
    }

    private ObservableList<Szczepionki> getSzczepionki(ResultSet rs) throws SQLException {

        ObservableList<Szczepionki> szczepionki = FXCollections.observableArrayList();

        while (rs.next()) {

            Szczepionki s = new Szczepionki();
            s.setTyp_szczepionki(rs.getString("typ_szczepionki"));
            szczepionki.add(s);
        }
        return szczepionki;
    }

    public ObservableList<String> showAllTypSzczepionki() throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT typ_szczepionki FROM szczepionki;";

        try {

            ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);

            ObservableList<Szczepionki> szczepionkis = getSzczepionki(resultSet);
            ObservableList<String> dostepne = FXCollections.observableArrayList();
            consoleTextArea.appendText(selectStmt + "\n");

            for (Szczepionki s : szczepionkis) {
                dostepne.add(s.getTyp_szczepionki());
            }

            return dostepne;


        } catch (SQLException e) {
            consoleTextArea.appendText("While searching deadlines, an error occurred. \n");
            throw e;
        }

    }

    public int selectTypSzczepionki(String typ) throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT id_szczepionki FROM szczepionki where typ_szczepionki like '" + typ + "';";

        try {

            ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);

            ObservableList<Szczepionki> szczepionkis = getAllFromSzczepionki(resultSet);
            ObservableList<Integer> szukanyT = FXCollections.observableArrayList();

            consoleTextArea.appendText(selectStmt);

            for (Szczepionki s : szczepionkis) {
                szukanyT.add(s.getId_szczepionki());
            }


            return szukanyT.get(0);


        } catch (SQLException e) {
            consoleTextArea.appendText("While searching id_szczepionki, an error occurred. \n");
            throw e;
        }

    }
}
