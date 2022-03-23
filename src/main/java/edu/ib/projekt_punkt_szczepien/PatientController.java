package edu.ib.projekt_punkt_szczepien;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PatientController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button wrocBtn;

    @FXML
    private Button addDeadlineBtn;

    @FXML
    private TableView yourDeadlinesTV;

    @FXML
    private TableColumn<TwojeTerminy, String> yourTypeCol;

    @FXML
    private TableColumn<TwojeTerminy, String> yourDeadlinesCol;

    @FXML
    private Button changeDeadlineBtn;

    @FXML
    private Button connectBtn;

    @FXML
    private TextArea consoleTextArea;

    @FXML
    private ChoiceBox<String> dateChoiceBox;

    @FXML
    private Button disconnectBtn;

    @FXML
    private TextField dobTF;

    @FXML
    private TableView freeDeadlinesTV;

    @FXML
    private TableColumn<ArchiwumSzczepienia, String> historyRealizCol;

    @FXML
    private TableColumn<ArchiwumSzczepienia, String> historyDeadlinesCol;

    @FXML
    private TableView historyTV;

    @FXML
    private TableColumn<ArchiwumSzczepienia, String> historyPeselCol;

    @FXML
    private TableColumn<ArchiwumSzczepienia, String> historyTypeCol;

    @FXML
    private TextField nameTF;

    @FXML
    private TableColumn<Terminy, String> newDeadlinesCol;

    @FXML
    private Button newPatientBtn;

    @FXML
    private Button oldPatientBtn;

    @FXML
    private TextField passwordTF;

    @FXML
    private TextField enterPeselTF;

    @FXML
    private TextField phoneTF;

    @FXML
    private Button removeBtn;

    @FXML
    private Button refreshBtn;

    @FXML
    private Button enterPeselBtn;

    @FXML
    private TextField surnameTF;

    @FXML
    private ChoiceBox<String> typeChoiceBox;

    @FXML
    private TextField usernameTF;

    @FXML
    private Button addPatientBtn;


    private DBUtil dbUtil;
    private PacjentDAO pacjentDAO;
    private ArchiwumSzczepieniaDAO archiwumSzczepieniaDAO;
    private TwojeTerminyDAO twojeTerminyDAO;

    @FXML
    void addDeadlineBtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {

            MozliwoscSzczepieniaDAO m = new MozliwoscSzczepieniaDAO(dbUtil, consoleTextArea);
            String sprawdz = m.showMozliwosc(enterPeselTF.getText());
            if (!enterPeselTF.getText().equals(null) && !typeChoiceBox.getValue().equals(null)
                    && !dateChoiceBox.getValue().equals(null)) {
                if (sprawdz.equals("true")) {
                    ZleceniaSzczepieniaDAO z = new ZleceniaSzczepieniaDAO(dbUtil, consoleTextArea);

                    z.insertZlecenie(enterPeselTF.getText(), dateChoiceBox.getValue(), typeChoiceBox.getValue());
                    consoleTextArea.appendText("New deadline for patient with PESEL =  " + enterPeselTF.getText() + " inserted." + "\n");

                    TerminyDAO t = new TerminyDAO(dbUtil, consoleTextArea);
                    t.deleteTerminy(dateChoiceBox.getValue());
                }else
                    consoleTextArea.appendText("You are not eligible for this vaccination!");
            }
        } catch (SQLException | ClassNotFoundException e) {
            consoleTextArea.appendText("Error occurred while inserting deadline.\n");
            throw e;
        }

    }

    @FXML
    void changeDeadlineBtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {

            if (!enterPeselTF.getText().equals(null) && !typeChoiceBox.getValue().equals(null)
                    && !dateChoiceBox.getValue().equals(null)) {
                ZleceniaSzczepieniaDAO z = new ZleceniaSzczepieniaDAO(dbUtil, consoleTextArea);

                z.updateTermin(enterPeselTF.getText(), dateChoiceBox.getValue(), typeChoiceBox.getValue());
                consoleTextArea.appendText("New deadline for patient with PESEL =  " + enterPeselTF.getText() + " inserted." + "\n");

                TerminyDAO t = new TerminyDAO(dbUtil, consoleTextArea);
                t.deleteTerminy(dateChoiceBox.getValue());


            }
        } catch (SQLException | ClassNotFoundException e) {
            consoleTextArea.appendText("Error occurred while updating deadline.\n");
            throw e;
        }

    }

    @FXML
    void newPatientOnActionBtn(ActionEvent event) {
        nameTF.setDisable(false);
        dobTF.setDisable(false);
        surnameTF.setDisable(false);
        phoneTF.setDisable(false);
        removeBtn.setDisable(true);

        addDeadlineBtn.setDisable(true);
        changeDeadlineBtn.setDisable(true);
        historyTV.setDisable(true);
        freeDeadlinesTV.setDisable(true);
        typeChoiceBox.setDisable(true);
        addPatientBtn.setDisable(false);
        historyTV.getItems().clear();
        freeDeadlinesTV.getItems().clear();
        typeChoiceBox.getItems().clear();
        yourDeadlinesTV.setDisable(true);
        dateChoiceBox.setDisable(true);
        refreshBtn.setDisable(true);


    }

    @FXML
    void oldPatientOnActionBtn(ActionEvent event) throws SQLException, ClassNotFoundException {
        removeBtn.setDisable(false);
        addDeadlineBtn.setDisable(false);
        changeDeadlineBtn.setDisable(false);
        historyTV.setDisable(false);
        freeDeadlinesTV.setDisable(false);
        typeChoiceBox.setDisable(false);
        nameTF.setDisable(true);
        dobTF.setDisable(true);
        surnameTF.setDisable(true);
        phoneTF.setDisable(true);

        addPatientBtn.setDisable(true);
        nameTF.clear();
        phoneTF.clear();
        surnameTF.clear();
        dobTF.clear();
        yourDeadlinesTV.setDisable(false);
        dateChoiceBox.setDisable(false);
        refreshBtn.setDisable(false);


    }

    @FXML
    void removeBtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {

            if (!enterPeselTF.getText().equals(null) && !typeChoiceBox.getValue().equals(null)) {
                ZleceniaSzczepieniaDAO z = new ZleceniaSzczepieniaDAO(dbUtil, consoleTextArea);

                z.deleteTermin(enterPeselTF.getText(), typeChoiceBox.getValue());
                consoleTextArea.appendText("Deleted deadline for patient with PESEL =  " + enterPeselTF.getText() + " inserted." + "\n");

            }
        } catch (SQLException | ClassNotFoundException e) {
            consoleTextArea.appendText("Error occurred while updating deadline.\n");
            throw e;
        }


    }

    @FXML
    void connectBtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        dbUtil = new DBUtil(usernameTF.getText(), passwordTF.getText(), consoleTextArea);
        pacjentDAO = new PacjentDAO(dbUtil, consoleTextArea);

        dbUtil.dbConnect();

        consoleTextArea.appendText("Access granted for user \"" + usernameTF.getText() + "\"." + "\n");
        connectBtn.setDisable(true);
        disconnectBtn.setDisable(false);
        oldPatientBtn.setDisable(false);
        newPatientBtn.setDisable(false);
        enterPeselTF.setDisable(false);
        enterPeselBtn.setDisable(false);

    }

    @FXML
    void disconnectBtnOnAction(ActionEvent event) {
        connectBtn.setDisable(false);
        disconnectBtn.setDisable(true);
        removeBtn.setDisable(true);
        oldPatientBtn.setDisable(true);
        newPatientBtn.setDisable(true);
        addDeadlineBtn.setDisable(true);
        changeDeadlineBtn.setDisable(true);
        historyTV.setDisable(true);
        freeDeadlinesTV.setDisable(true);
        typeChoiceBox.setDisable(true);
        nameTF.setDisable(true);
        dobTF.setDisable(true);
        surnameTF.setDisable(true);
        phoneTF.setDisable(true);
        enterPeselTF.setDisable(true);
        enterPeselBtn.setDisable(true);
        yourDeadlinesTV.setDisable(true);
        dateChoiceBox.setDisable(true);
        refreshBtn.setDisable(true);


    }

    @FXML
    void initialize() {
        assert addDeadlineBtn != null : "fx:id=\"addDeadlineBtn\" was not injected: check your FXML file 'patientController.fxml'.";
        assert changeDeadlineBtn != null : "fx:id=\"changeDeadlineBtn\" was not injected: check your FXML file 'patientController.fxml'.";
        assert consoleTextArea != null : "fx:id=\"consoleTextArea\" was not injected: check your FXML file 'patientController.fxml'.";
        assert dobTF != null : "fx:id=\"dobTF\" was not injected: check your FXML file 'patientController.fxml'.";
        assert freeDeadlinesTV != null : "fx:id=\"freeDeadlinesTV\" was not injected: check your FXML file 'patientController.fxml'.";
        assert historyDeadlinesCol != null : "fx:id=\"historyDeadlinesCol\" was not injected: check your FXML file 'patientController.fxml'.";
        assert historyTV != null : "fx:id=\"historyTV\" was not injected: check your FXML file 'patientController.fxml'.";
        assert yourDeadlinesTV != null : "fx:id=\"historyTV\" was not injected: check your FXML file 'patientController.fxml'.";
        assert historyTypeCol != null : "fx:id=\"historyTypeCol\" was not injected: check your FXML file 'patientController.fxml'.";
        assert nameTF != null : "fx:id=\"nameTF\" was not injected: check your FXML file 'patientController.fxml'.";
        assert newDeadlinesCol != null : "fx:id=\"newDeadlinesCol\" was not injected: check your FXML file 'patientController.fxml'.";
        assert newPatientBtn != null : "fx:id=\"newPatientBtn\" was not injected: check your FXML file 'patientController.fxml'.";
        assert oldPatientBtn != null : "fx:id=\"oldPatientBtn\" was not injected: check your FXML file 'patientController.fxml'.";
        assert passwordTF != null : "fx:id=\"passwordTF\" was not injected: check your FXML file 'patientController.fxml'.";
        assert enterPeselTF != null : "fx:id=\"peselTF\" was not injected: check your FXML file 'patientController.fxml'.";
        assert phoneTF != null : "fx:id=\"phoneTF\" was not injected: check your FXML file 'patientController.fxml'.";
        assert removeBtn != null : "fx:id=\"removeBtn\" was not injected: check your FXML file 'patientController.fxml'.";
        assert surnameTF != null : "fx:id=\"surnameTF\" was not injected: check your FXML file 'patientController.fxml'.";
        assert typeChoiceBox != null : "fx:id=\"typeChoiceBox\" was not injected: check your FXML file 'patientController.fxml'.";
        assert usernameTF != null : "fx:id=\"usernameTF\" was not injected: check your FXML file 'patientController.fxml'.";
        assert addDeadlineBtn != null : "fx:id=\"addDeadlineBtn\" was not injected: check your FXML file 'patientController.fxml'.";
        assert changeDeadlineBtn != null : "fx:id=\"changeDeadlineBtn\" was not injected: check your FXML file 'patientController.fxml'.";
        assert connectBtn != null : "fx:id=\"connectBtn\" was not injected: check your FXML file 'patientController.fxml'.";
        assert consoleTextArea != null : "fx:id=\"consoleTextArea\" was not injected: check your FXML file 'patientController.fxml'.";
        assert disconnectBtn != null : "fx:id=\"disconnectBtn\" was not injected: check your FXML file 'patientController.fxml'.";
        assert historyRealizCol != null : "fx:id=\"histortyRealizCol\" was not injected: check your FXML file 'patientController.fxml'.";


    }

    public void addPatientBtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {

            if (!enterPeselTF.getText().equals(null) && !phoneTF.getText().equals(null)
                    && !dobTF.getText().equals(null) && !nameTF.getText().equals(null) && !surnameTF.getText().equals(null)) {

                pacjentDAO.insertPacjent(nameTF.getText(), surnameTF.getText(), enterPeselTF.getText(), phoneTF.getText(), dobTF.getText());
                consoleTextArea.appendText("New patient who PESEL =  " + enterPeselTF.getText() + " inserted." + "\n");

            }
        } catch (SQLException | ClassNotFoundException e) {
            consoleTextArea.appendText("Error occurred while inserting patient.\n");
            throw e;
        }
    }

    public void enterPeselBtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        showYourDeadlines();
        showFreeDeadlines();
        enterTerminyToCB();
        enterSzczepionkiToCB();
        showHistory();

    }

    public void showFreeDeadlines() throws SQLException, ClassNotFoundException {
        try {
            if (!enterPeselTF.getText().equals(null)) {
                freeDeadlinesTV.getItems().clear();
                newDeadlinesCol.setCellValueFactory(new PropertyValueFactory<Terminy, String>("termin"));
                TerminyDAO terminyDAO = new TerminyDAO(dbUtil, consoleTextArea);
                ObservableList<Terminy> wolne = terminyDAO.showAllTerminy();
                freeDeadlinesTV.setItems(wolne);
            }
        } catch (SQLException | ClassNotFoundException e) {
            consoleTextArea.appendText("Error occurred while getting data from DB.\n");
            throw e;
        }

    }

    public void showYourDeadlines() throws SQLException, ClassNotFoundException {
        try {
            if (!enterPeselTF.getText().equals(null)) {
                yourDeadlinesTV.getItems().clear();
                yourDeadlinesCol.setCellValueFactory(new PropertyValueFactory<TwojeTerminy, String>("termin"));
                yourTypeCol.setCellValueFactory(new PropertyValueFactory<TwojeTerminy, String>("typ_szczepionki"));
                twojeTerminyDAO = new TwojeTerminyDAO(dbUtil, consoleTextArea);
                ObservableList<TwojeTerminy> twoje = twojeTerminyDAO.showTwojeTerminy(enterPeselTF.getText());
                yourDeadlinesTV.setItems(twoje);
            }
        } catch (SQLException | ClassNotFoundException e) {
            consoleTextArea.appendText("Error occurred while getting data from DB.\n");
            throw e;
        }
    }

    public void showHistory() throws SQLException, ClassNotFoundException {
        try {
            if (!enterPeselTF.getText().equals(null)) {
                historyTV.getItems().clear();
                historyTypeCol.setCellValueFactory(new PropertyValueFactory<ArchiwumSzczepienia, String>("typ_szczepionki"));
                historyRealizCol.setCellValueFactory(new PropertyValueFactory<ArchiwumSzczepienia, String>("realizacja"));
                historyDeadlinesCol.setCellValueFactory(new PropertyValueFactory<ArchiwumSzczepienia, String>("termin"));
                archiwumSzczepieniaDAO = new ArchiwumSzczepieniaDAO(dbUtil, consoleTextArea);
                ObservableList<ArchiwumSzczepienia> archiwumData = archiwumSzczepieniaDAO.searchArchiwum(enterPeselTF.getText());
                historyTV.setItems(archiwumData);

            }
        } catch (SQLException | ClassNotFoundException e) {
            consoleTextArea.appendText("Error occurred while getting data from DB.\n");
            throw e;
        }
    }

    public void enterTerminyToCB() throws SQLException, ClassNotFoundException {
        try {
            if (!enterPeselTF.getText().equals(null)) {
                TerminyDAO terminyDAO = new TerminyDAO(dbUtil, consoleTextArea);
                ObservableList<String> terminy = terminyDAO.showAvailableTerminy();
                dateChoiceBox.setItems(terminy);

            }
        } catch (SQLException | ClassNotFoundException e) {
            consoleTextArea.appendText("Error occurred while getting data from DB.\n");
            throw e;
        }
    }

    public void enterSzczepionkiToCB() throws SQLException, ClassNotFoundException {
        try {
            if (!enterPeselTF.getText().equals(null)) {
                SzczepionkiDAO szczepionkiDAO = new SzczepionkiDAO(dbUtil, consoleTextArea);
                ObservableList<String> szczep = szczepionkiDAO.showAllTypSzczepionki();
                typeChoiceBox.setItems(szczep);

            }
        } catch (SQLException | ClassNotFoundException e) {
            consoleTextArea.appendText("Error occurred while getting data from DB.\n");
            throw e;
        }
    }

    public void createStartMenuStage() {
        try {
            Parent root =  FXMLLoader.load(getClass().getResource("startMenu.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 600, 400));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void wrocBtnOnAction(ActionEvent event) {
        Stage stage = (Stage) wrocBtn.getScene().getWindow();
        stage.close();
         createStartMenuStage();
    }

    public void refreshBtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        showYourDeadlines();
        showFreeDeadlines();
        enterTerminyToCB();
        enterSzczepionkiToCB();
        showHistory();
    }
}

