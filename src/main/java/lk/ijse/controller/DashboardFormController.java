package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardFormController {
    @FXML
    private AnchorPane Root;
    @FXML
    private AnchorPane root;

    public void initialize() throws IOException {
        this.Root.getChildren().clear();
        this.Root.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/main_dashboard_form.fxml")));
    }

    public void btnPassengerOnAction(ActionEvent actionEvent) throws IOException {
        this.Root.getChildren().clear();
        this.Root.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/passenger_form.fxml")));
    }

    public void btnTrainOnAction(ActionEvent actionEvent) throws IOException {
        this.Root.getChildren().clear();
        this.Root.getChildren().add(FXMLLoader.load(getClass().getResource("/view/train_form.fxml")));
    }

    public void btnEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        this.Root.getChildren().clear();
        this.Root.getChildren().add(FXMLLoader.load(getClass().getResource("/view/employee_form.fxml")));

    }

    public void btnTicketOnAction(ActionEvent actionEvent) throws IOException{
        this.Root.getChildren().clear();
        this.Root.getChildren().add(FXMLLoader.load(getClass().getResource("/view/reservation_form.fxml")));
    }

    public void btnAttendanceOnAction(ActionEvent actionEvent) throws IOException {
        this.Root.getChildren().clear();
        this.Root.getChildren().add(FXMLLoader.load(getClass().getResource("/view/attendance_form.fxml")));
    }

    public void btnScheduleOnAction(ActionEvent actionEvent) throws IOException {
        this.Root.getChildren().clear();
        this.Root.getChildren().add(FXMLLoader.load(getClass().getResource("/view/schedule_form.fxml")));
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Page");
        stage.centerOnScreen();
    }

    public void btnDeliveryOnAction(ActionEvent actionEvent) throws IOException {
        this.Root.getChildren().clear();
        this.Root.getChildren().add(FXMLLoader.load(getClass().getResource("/view/delivery_form.fxml")));
    }

    public void btnDashboardOnAction(ActionEvent actionEvent) throws IOException {
        this.Root.getChildren().clear();
        this.Root.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/main_dashboard_form.fxml")));
    }

    public void btnPayrollOnAction(ActionEvent actionEvent) throws IOException {
        this.Root.getChildren().clear();
        this.Root.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/payroll_form.fxml")));
    }
}
