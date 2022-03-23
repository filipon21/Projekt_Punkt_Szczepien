package edu.ib.projekt_punkt_szczepien;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TerminyDAO {
    private DBUtil dbUtil;
    private TextArea consoleTextArea;

    public TerminyDAO(DBUtil dbUtil, TextArea consoleTextArea) {
        this.dbUtil = dbUtil;
        this.consoleTextArea = consoleTextArea;
    }

    private ObservableList<Terminy> getTerminy(ResultSet rs) throws SQLException {

        ObservableList<Terminy> terminy = FXCollections.observableArrayList();

        while (rs.next()) {

            Terminy t = new Terminy();
            t.setTermin(rs.getString("termin"));
            terminy.add(t);
        }
        return terminy;
    }

    public ObservableList<Terminy> showAllTerminy() throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT * FROM terminy;";

        try {

            ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);

            ObservableList<Terminy> terminy = getTerminy(resultSet);
            consoleTextArea.appendText(selectStmt);

            return terminy;


        } catch (SQLException e) {
            consoleTextArea.appendText("While searching deadlines, an error occurred. \n");
            throw e;
        }

    }

    public ObservableList<String> showAvailableTerminy() throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT * FROM terminy;";

        try {

            ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);
            ObservableList<Terminy> terminy = getTerminy(resultSet);
            ObservableList<String> stringObservableList = FXCollections.observableArrayList();

            consoleTextArea.appendText(selectStmt + "\n");

            for (Terminy t : terminy){
                stringObservableList.add(t.getTermin());
            }
            return stringObservableList;


        } catch (SQLException e) {
            consoleTextArea.appendText("While searching deadlines, an error occurred. \n");
            throw e;
        }

    }

    public void deleteTerminy(String date) throws SQLException, ClassNotFoundException {
        StringBuilder sb = new StringBuilder("DELETE from terminy where termin = '");
        sb.append(date);
        sb.append("';");
        String updateStmt = sb.toString();

        try {
            dbUtil.dbExecuteUpdate(updateStmt);

        } catch (SQLException e) {
            consoleTextArea.appendText("Error occurred while UPDATE Operation." + "\n");
            throw e;
        }
    }

    public void insertTermin(String data, String godzina)
            throws SQLException, ClassNotFoundException {

        StringBuilder sb = new StringBuilder("INSERT INTO terminy(termin) VALUES('");
        sb.append(data);
        sb.append(" ");
        sb.append(godzina);
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
