package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import lk.ijse.dto.PassengerDto;
import lk.ijse.model.PassengerModel;

import java.sql.SQLException;
import java.util.List;

public class PassengerDeleteFormController {

    public ComboBox txtId;
    PassengerModel model = new PassengerModel();

    public void initialize() {
        loadAllPassengerID();
    }

    private void loadAllPassengerID() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<PassengerDto> IdDtos = PassengerModel.getAllPassengerId();

            for (PassengerDto dto : IdDtos) {
                obList.add(dto.getId());
            }
            txtId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletebtn(ActionEvent actionEvent) {
        String id = txtId.getSelectionModel().getSelectedItem().toString();
        try {
            model.deletePassenger(id);
            new Alert(Alert.AlertType.INFORMATION, "Deleted").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Try Again").show();
        }
    }


    public void clearbtn(ActionEvent actionEvent) {
    }
}
