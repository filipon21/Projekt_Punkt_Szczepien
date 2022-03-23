package edu.ib.projekt_punkt_szczepien;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkerController {

    private Connection connection;

    @FXML
    private Button connectBtn;

    @FXML
    private TextArea consonleTextArea;

    @FXML
    private TextField dataTf;

    @FXML
    private Button disconnectBtn;

    @FXML
    private DatePicker doTf;

    @FXML
    private PasswordField hasloTf;

    @FXML
    private TextField nazwaUzytkownikaTf;

    @FXML
    private DatePicker odTf;

    @FXML
    private TableColumn<ZleceniaSzczepienia, String> peselCol;

    @FXML
    private TextField peselTf;

    @FXML
    private Button pokazBtn;

    @FXML
    private TableColumn<ZleceniaSzczepienia, String> rodzajCol;

    @FXML
    private TableColumn<ZleceniaSzczepienia, String> idCol;

    @FXML
    private BarChart<?, ?> statystykiChart;

    @FXML
    private TableColumn<ZleceniaSzczepienia, String> terminCol;

    @FXML
    private TableView terminyTV;

    @FXML
    private Button wrocBtn;

    @FXML
    private Button zrealizujBtn;

    @FXML
    private Button odswiezBtn;

    @FXML
    private TextField idTf;

    @FXML
    private DatePicker datePicker;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;


    private DBUtil dbUtil;

    @FXML
    void connectBtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        dbUtil = new DBUtil(nazwaUzytkownikaTf.getText(), hasloTf.getText(), consonleTextArea);
        dbUtil.dbConnect();
        consonleTextArea.appendText("Access granted for user \"" + nazwaUzytkownikaTf.getText() + "\"." + "\n");
        connectBtn.setDisable(true);
        disconnectBtn.setDisable(false);
        dataTf.setDisable(false);
        peselTf.setDisable(false);
        zrealizujBtn.setDisable(false);
        odTf.setDisable(false);
        doTf.setDisable(false);
        pokazBtn.setDisable(false);
        odswiezBtn.setDisable(false);
        terminyTV.setDisable(false);
        idTf.setDisable(false);
        datePicker.setDisable(false);
        showZlecenia();

    }

    @FXML
    void disconnectBtnOnAction(ActionEvent event) {
        connectBtn.setDisable(false);
        disconnectBtn.setDisable(true);
        dataTf.setDisable(true);
        peselTf.setDisable(true);
        zrealizujBtn.setDisable(true);
        odTf.setDisable(true);
        doTf.setDisable(true);
        pokazBtn.setDisable(true);
        odswiezBtn.setDisable(true);
        terminyTV.setDisable(true);
        datePicker.setDisable(true);

        idTf.setDisable(true);
    }

    @FXML
    void pokazOnActionBtn(ActionEvent event) throws SQLException, ClassNotFoundException {
        makeGraph();
    }

    @FXML
    void zrealizujOnActionBtn(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {

            if (!peselTf.getText().equals(null) && !idTf.getText().equals(null)) {
                ZleceniaSzczepieniaDAO z = new ZleceniaSzczepieniaDAO(dbUtil, consonleTextArea);

                z.deleteOrder(peselTf.getText(), idTf.getText());
                consonleTextArea.appendText("Order done for patient with PESEL =  " + peselTf.getText() + "." + "\n");

            }
        } catch (SQLException | ClassNotFoundException e) {
            consonleTextArea.appendText("Error occurred while updating deadline.\n");
            throw e;
        }

    }

    @FXML
    void initialize() {
        assert consonleTextArea != null : "fx:id=\"consoleTextArea\" was not injected: check your FXML file 'patientController.fxml'.";
        assert connectBtn != null : "fx:id=\"connectBtn\" was not injected: check your FXML file 'patientController.fxml'.";
        assert disconnectBtn != null : "fx:id=\"disconnectBtn\" was not injected: check your FXML file 'patientController.fxml'.";
        assert dataTf != null : "fx:id=\"dataTf\" was not injected: check your FXML file 'patientController.fxml'.";
        assert peselTf != null : "fx:id=\"peselTf\" was not injected: check your FXML file 'patientController.fxml'.";
        assert zrealizujBtn != null : "fx:id=\"zrealizujBtn\" was not injected: check your FXML file 'patientController.fxml'.";
        assert odTf != null : "fx:id=\"odTf\" was not injected: check your FXML file 'patientController.fxml'.";
        assert doTf != null : "fx:id=\"doTf\" was not injected: check your FXML file 'patientController.fxml'.";
        assert pokazBtn != null : "fx:id=\"pokazBtn\" was not injected: check your FXML file 'patientController.fxml'.";
    }

    public void showZlecenia() throws SQLException, ClassNotFoundException {
        try {
            terminyTV.getItems().clear();
            peselCol.setCellValueFactory(new PropertyValueFactory<ZleceniaSzczepienia, String>("pesel"));
            terminCol.setCellValueFactory(new PropertyValueFactory<ZleceniaSzczepienia, String>("termin"));
            rodzajCol.setCellValueFactory(new PropertyValueFactory<ZleceniaSzczepienia, String>("typ_szczepionki"));
            idCol.setCellValueFactory(new PropertyValueFactory<ZleceniaSzczepienia, String>("numer_zlecenia"));

            ZleceniaSzczepieniaDAO z = new ZleceniaSzczepieniaDAO(dbUtil, consonleTextArea);
            ObservableList<ZleceniaSzczepienia> zleceniaData = z.showAllZlecenia();
            terminyTV.setItems(zleceniaData);


        } catch (SQLException | ClassNotFoundException e) {
            consonleTextArea.appendText("Error occurred while getting data from DB.\n");
            throw e;
        }
    }

    public void odwiezOnActionBtn(ActionEvent event) throws SQLException, ClassNotFoundException {
        showZlecenia();
        makeGraph();

    }

    public void wrocBtnOnAction(ActionEvent event) {
        Stage stage = (Stage) wrocBtn.getScene().getWindow();
        stage.close();
        createStartMenuStage();
    }

    public void createStartMenuStage() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("startMenu.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 600, 400));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void dodajTerminBtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {

            if (!datePicker.getValue().equals(null) && !dataTf.getText().equals(null)) {

                TerminyDAO z = new TerminyDAO(dbUtil, consonleTextArea);

                z.insertTermin(datePicker.getValue().toString(), dataTf.getText());
                consonleTextArea.appendText("New deadline inserted." + "\n");

            }
        } catch (SQLException | ClassNotFoundException e) {
            consonleTextArea.appendText("Error occurred while inserting deadline.\n");
            throw e;
        }
    }
    public void makeGraph() throws SQLException, ClassNotFoundException {
        try {

            if (!odTf.getValue().equals(null) && !doTf.getValue().equals(null)) {
                ArchiwumSzczepieniaDAO a = new ArchiwumSzczepieniaDAO(dbUtil,consonleTextArea);
                ObservableList<String> szczepionki = a.selectedSzczepionki(odTf.getValue().toString(),doTf.getValue().toString());
                ObservableList<Integer> ilosc = a.selectedAmount(odTf.getValue().toString(),doTf.getValue().toString());

                xAxis.setLabel("Rodzaj szczepionki");
                yAxis.setLabel("Ilość szczepień");
                XYChart.Series series1 = new XYChart.Series();
                for (int i = 0; i<szczepionki.size();i++){
                    series1.getData().add(new XYChart.Data(szczepionki.get(i),ilosc.get(i)));
                }
                statystykiChart.getData().addAll(series1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            consonleTextArea.appendText("Error occurred while inserting deadline.\n");
            throw e;
        }
    }
}
