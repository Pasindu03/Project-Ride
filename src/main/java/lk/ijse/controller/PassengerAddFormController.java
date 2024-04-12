package lk.ijse.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.PassengerDto;
import lk.ijse.model.PassengerModel;

public class PassengerAddFormController {
    public AnchorPane root;
    public JFXTextField txtId;
    public JFXTextField txtAge;
    public JFXTextField txtName;
    public JFXTextField txtGender;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    PassengerModel model = new PassengerModel();


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
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void addbtn(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String age = txtAge.getText();
        String gender = txtGender.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();

        var dto = new PassengerDto(id, name, age, gender, address, contact);

        try {
            boolean isSaved = model.addPassenger(dto);

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer saved!").show();
                clearFields();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void clearFields() {
        txtId.clear();
        txtName.clear();
        txtAge.clear();
        txtGender.clear();
        txtAddress.clear();
        txtContact.clear();
    }

    public void backbtn(ActionEvent actionEvent) {
        clearFields();
    }
}
