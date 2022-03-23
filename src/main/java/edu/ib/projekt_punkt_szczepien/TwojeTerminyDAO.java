package edu.ib.projekt_punkt_szczepien;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TwojeTerminyDAO {
    private DBUtil dbUtil;
    private TextArea consoleTextArea;

    public TwojeTerminyDAO(DBUtil dbUtil, TextArea consoleTextArea) {
        this.dbUtil = dbUtil;
        this.consoleTextArea = consoleTextArea;
    }

    private ObservableList<TwojeTerminy> getTwojeTerminy(ResultSet rs) throws SQLException {

        ObservableList<TwojeTerminy> terminy = FXCollections.observableArrayList();

        while (rs.next()) {

            TwojeTerminy t = new TwojeTerminy();
            t.setTyp_szczepionki(rs.getString("typ_szczepionki"));
            t.setTermin(rs.getString("termin"));
            terminy.add(t);
        }
        return terminy;
    }

    public ObservableList<TwojeTerminy> showTwojeTerminy(String pesel) throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT typ_szczepionki, termin FROM twojeTerminy where pesel like '" + pesel + "';";

        try {

            ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);

            ObservableList<TwojeTerminy> terminy = getTwojeTerminy(resultSet);
            consoleTextArea.appendText(selectStmt);

            return terminy;


        } catch (SQLException e) {
            consoleTextArea.appendText("While searching deadlines, an error occurred. \n");
            throw e;
        }

    }

}
