package lk.ijse.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import lk.ijse.dto.PassengerDto;
import lk.ijse.model.PassengerModel;

import java.sql.SQLException;

public class PassengerUpdateFormController {

    public JFXTextField txtId;
    public JFXTextField txtAge;
    public JFXTextField txtName;
    public JFXTextField txtGender;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    PassengerModel model = new PassengerModel();

    public void updatebtn(ActionEvent actionEvent) {
        String id = txtId.getText();
        String age = txtAge.getText();
        String name = txtName.getText();
        String gender = txtGender.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();

        var dto = new PassengerDto(id,age,name,gender,address,contact);

        var model = new PassengerModel();
        try {
            boolean isUpdated = model.updatePassenger(dto);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Passenger updated!").show();
                clearFields();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void clearFields() {
        txtId.clear();
        txtAge.clear();
        txtName.clear();
        txtGender.clear();
        txtAddress.clear();
        txtContact.clear();
    }

    public void backbtn(ActionEvent actionEvent) {
        clearFields();
    }

    public void txtOnActionSearch(ActionEvent actionEvent) {
        String id = txtId.getText();

        try {
            PassengerDto dto = model.searchPassenger(id);
            if (dto != null) {
                txtId.setText(dto.getId());
                txtName.setText(dto.getName());
                txtAge.setText(dto.getAge());
                txtGender.setText(dto.getGender());
                txtAddress.setText(dto.getAddress());
                txtContact.setText(dto.getContact());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
