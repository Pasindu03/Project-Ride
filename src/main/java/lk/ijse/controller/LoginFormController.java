package lk.ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dto.UserDto;
import lk.ijse.model.UserModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LoginFormController {
    public JFXPasswordField txtPassword;
    public JFXComboBox txtUsername;
    public AnchorPane root;

    public void btnOnActionLogin(ActionEvent actionEvent) throws IOException {
        try {
            String user_name = txtUsername.getSelectionModel().getSelectedItem().toString();

            UserDto user = UserModel.searchUser(user_name);

            if (user.getPassword().equals(txtPassword.getText())) {
                Stage currentStage = (Stage) root.getScene().getWindow();
                currentStage.close();

                Stage newStage = new Stage();
                newStage.setScene(new Scene(new FXMLLoader(getClass().getResource("/view/dashboard_form.fxml")).load()));
                newStage.show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid Password").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Please Select Username!").show();
        }
    }

    @FXML
    public void initialize() {
        loadId();
    }

    public void txtOnActionLogin() {
        try {
            String user_name = txtUsername.getSelectionModel().getSelectedItem().toString();

            UserDto user = UserModel.searchUser(user_name);

            if (user.getPassword().equals(txtPassword.getText())) {
                Stage currentStage = (Stage) root.getScene().getWindow();
                currentStage.close();

                Stage newStage = new Stage();
                newStage.setScene(new Scene(new FXMLLoader(getClass().getResource("/view/dashboard_form.fxml")).load()));
                newStage.show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Please Select Username!").show();
        }
    }

    public void loadId(){
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<UserDto> IdDtos = UserModel.getAllId();

            for (UserDto dto : IdDtos) {
                obList.add(dto.getUsername());
            }
            txtUsername.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void btnOnActionForgot(ActionEvent actionEvent) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/new_user_form.fxml")));
    }

    public void PressedOnEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            txtOnActionLogin();
        }
    }
}