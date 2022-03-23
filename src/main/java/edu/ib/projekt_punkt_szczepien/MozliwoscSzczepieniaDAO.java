package edu.ib.projekt_punkt_szczepien;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MozliwoscSzczepieniaDAO {
    private DBUtil dbUtil;
    private TextArea consoleTextArea;

    public MozliwoscSzczepieniaDAO(DBUtil dbUtil, TextArea consoleTextArea) {
        this.dbUtil = dbUtil;
        this.consoleTextArea = consoleTextArea;
    }

    private ObservableList<MozliwoscSzczepienia> getMozliwosc(ResultSet rs) throws SQLException {

        ObservableList<MozliwoscSzczepienia> mozliwosc = FXCollections.observableArrayList();

        while (rs.next()) {

            MozliwoscSzczepienia r = new MozliwoscSzczepienia();

            r.setCzy_moze_szczepic(rs.getString("czy_moze_szczepic"));

            mozliwosc.add(r);
        }
        return mozliwosc;
    }


    public String showMozliwosc(String pesel) throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT czy_moze_szczepic FROM mozliwosc_szczepienia where pesel like '" + pesel + "';";

        try {

            ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);
            ObservableList<MozliwoscSzczepienia> m = getMozliwosc(resultSet);
            ObservableList<String> integers = FXCollections.observableArrayList();

            consoleTextArea.appendText(selectStmt);

            for (MozliwoscSzczepienia t : m) {
                integers.add(t.getCzy_moze_szczepic());
            }


            String found = integers.get(0);
            return found;


        } catch (SQLException e) {
            consoleTextArea.appendText("While inserting deadline, an error occurred. \n");
            throw e;
        }

    }
}