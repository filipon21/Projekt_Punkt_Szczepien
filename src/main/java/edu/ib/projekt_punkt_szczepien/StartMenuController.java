package edu.ib.projekt_punkt_szczepien;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartMenuController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button patientBtn;

    @FXML
    private Button workerBtn;

    @FXML
    private ImageView firstAidKitImageView;

    @FXML
    private Button exitBtn;

    @FXML
    void patientBtnOnAction(ActionEvent event) {
        Stage stage = (Stage) patientBtn.getScene().getWindow();
        stage.close();
        createLoginPatientStage();

    }

    @FXML
    void workerBtnOnAction(ActionEvent event) {
        Stage stage = (Stage) workerBtn.getScene().getWindow();
        stage.close();
        createLoginWorkerStage();

    }

    @FXML
    void initialize() {
        assert patientBtn != null : "fx:id=\"patientBtn\" was not injected: check your FXML file 'startMenu.fxml'.";
        assert workerBtn != null : "fx:id=\"workerBtn\" was not injected: check your FXML file 'startMenu.fxml'.";

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File firstAidKitFile = new File("Images/apteczka.jpg");
        Image firstAidKitImage = new Image(firstAidKitFile.toURI().toString());
        firstAidKitImageView.setImage(firstAidKitImage);
    }

    public void createLoginPatientStage() {
        try {
            Parent root =  FXMLLoader.load(getClass().getResource("patientController.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 920, 865));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void createLoginWorkerStage() {
        try {
            Parent root =  FXMLLoader.load(getClass().getResource("workerController.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 917, 862));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void exitBtnOnAction(ActionEvent event) {
        Stage stage = (Stage) exitBtn.getScene().getWindow();
        stage.close();
        Platform.exit();
    }
}
