package lk.ijse.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dto.UserDto;
import lk.ijse.model.UserModel;

import java.io.IOException;
import java.util.regex.Pattern;

public class NewUserController {
    public AnchorPane root;
    public JFXTextField txtUsername;
    public JFXTextField txtPassword;
    public JFXTextField txtEmail;
    private UserModel model = new UserModel();

    public void EnterOnPassword() {
        txtPassword.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                // Enter key is pressed, perform the login action
                EnterOnPassword();
            }
        });
    }

    public void initialize(){
        txtPassword.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                // Enter key is pressed, perform the login action
                EnterOnPassword();
            }
        });
    }

    public void btnAddUserOnAction(ActionEvent actionEvent) {
        boolean isValid = validateInput();

        if (isValid){
        UserDto dto = new UserDto(
                txtEmail.getText(),
                txtUsername.getText(),
                txtPassword.getText()
        );

        try {
            boolean isSaved = model.addUser(dto);

            if (isSaved) {
                this.root.getChildren().clear();
                this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml")));
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        }
    }

    private boolean validateInput() {
        boolean matchEmail = Pattern.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", txtEmail.getText());
        boolean matchId = Pattern.matches("[U]\\d{3,}", txtUsername.getText());
        boolean matchPassword = Pattern.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", txtPassword.getText());

        if (!matchEmail) {
            new Alert(Alert.AlertType.ERROR, "Please Enter A Valid Email like \"example@example.com\" ").show();
            return false;
        }

        if (!matchId) {
            new Alert(Alert.AlertType.ERROR, "Enter a valid ID like \"U001\"").show();
            return false;
        }

        if (!matchPassword) {
            new Alert(Alert.AlertType.ERROR, "Has minimum 8 characters in length/n At least one uppercase English letter/n At least one lowercase English letter/n At least one digit/n At least one special character").show();
            return false;
        }

        return true;
    }
    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Page");
        stage.centerOnScreen();
    }
}
