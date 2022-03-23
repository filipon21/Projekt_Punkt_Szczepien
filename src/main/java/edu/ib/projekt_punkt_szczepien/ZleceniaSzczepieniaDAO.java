package edu.ib.projekt_punkt_szczepien;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ZleceniaSzczepieniaDAO {
    private DBUtil dbUtil;
    private TextArea consoleTextArea;

    public ZleceniaSzczepieniaDAO(DBUtil dbUtil, TextArea consoleTextArea) {
        this.dbUtil = dbUtil;
        this.consoleTextArea = consoleTextArea;
    }

    private ObservableList<ZleceniaSzczepienia> getZleceniaSzczepienia(ResultSet rs) throws SQLException {

        ObservableList<ZleceniaSzczepienia> zlecenia = FXCollections.observableArrayList();

        while (rs.next()) {

            ZleceniaSzczepienia z = new ZleceniaSzczepienia();
            z.setPesel(rs.getString("pesel"));
            z.setTermin(rs.getString("termin"));
            z.setTyp_szczepionki(rs.getString("typ_szczepionki"));
            zlecenia.add(z);
        }
        return zlecenia;
    }

    public void insertZlecenie(String pesel, String termin, String typ)
            throws SQLException, ClassNotFoundException {

        StringBuilder sb = new StringBuilder("INSERT INTO zlecenia_szczepienia(pesel, termin, typ_szczepionki) VALUES('");
        sb.append(pesel);
        sb.append("','");
        sb.append(termin);
        sb.append("','");
        sb.append(typ);
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

    public void updateTermin(String pesel, String date, String type) throws SQLException, ClassNotFoundException {
        StringBuilder sb = new StringBuilder("UPDATE zlecenia_szczepienia SET termin = '");
        sb.append(date);
        sb.append("' where pesel = '");
        sb.append(pesel);
        sb.append("' and typ_szczepionki ='");
        sb.append(type);
        sb.append("';");
        String updateStmt = sb.toString();

        try {
            dbUtil.dbExecuteUpdate(updateStmt);

        } catch (SQLException e) {
            consoleTextArea.appendText("Error occurred while UPDATE Operation." + "\n");
            throw e;
        }
    }

    public void deleteTermin(String pesel, String type) throws SQLException, ClassNotFoundException {
        StringBuilder sb = new StringBuilder("DELETE from zlecenia_szczepienia where pesel = '");
        sb.append(pesel);
        sb.append("' and typ_szczepionki ='");
        sb.append(type);
        sb.append("';");
        String updateStmt = sb.toString();

        try {
            dbUtil.dbExecuteUpdate(updateStmt);

        } catch (SQLException e) {
            consoleTextArea.appendText("Error occurred while delete Operation." + "\n");
            throw e;
        }
    }

    private ObservableList<ZleceniaSzczepienia> getAllFromZlecenia(ResultSet rs) throws SQLException {

        ObservableList<ZleceniaSzczepienia> zlecenia = FXCollections.observableArrayList();

        while (rs.next()) {

            ZleceniaSzczepienia z = new ZleceniaSzczepienia();
            z.setNumer_zlecenia(rs.getInt("numer_zlecenia"));
            z.setPesel(rs.getString("pesel"));
            z.setTermin(rs.getString("termin"));
            z.setTyp_szczepionki(rs.getString("typ_szczepionki"));

            zlecenia.add(z);
        }
        return zlecenia;
    }

    public ObservableList<ZleceniaSzczepienia> showAllZlecenia() throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT * FROM zlecenia_szczepienia;";

        try {

            ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);

            ObservableList<ZleceniaSzczepienia> zlecenia = getAllFromZlecenia(resultSet);
            consoleTextArea.appendText(selectStmt);

            return zlecenia;


        } catch (SQLException e) {
            consoleTextArea.appendText("While searching all deadlines, an error occurred. \n");
            throw e;
        }

    }
    public void deleteOrder(String pesel, String nr) throws SQLException, ClassNotFoundException {
        StringBuilder sb = new StringBuilder("DELETE from zlecenia_szczepienia where pesel = '");
        sb.append(pesel);
        sb.append("' and numer_zlecenia =");
        sb.append(nr);
        sb.append(";");
        String updateStmt = sb.toString();

        try {
            dbUtil.dbExecuteUpdate(updateStmt);

        } catch (SQLException e) {
            consoleTextArea.appendText("Error occurred while delete Operation." + "\n");
            throw e;
        }
    }
}
