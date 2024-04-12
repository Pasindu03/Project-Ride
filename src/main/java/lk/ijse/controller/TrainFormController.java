package lk.ijse.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Tm.TrainTm;
import lk.ijse.dto.TrainDto;
import lk.ijse.model.TrainModel;

import java.io.IOException;
import java.util.List;

public class TrainFormController {
    public AnchorPane root;
    public JFXTextField txtID;
    public JFXTextField txtNoOfSeats;
    public JFXTextField txtType;
    public JFXTextField txtStatus;
    public TableView tblTrains;
    public TableColumn colTrainID;
    public TableColumn colType;
    public TableColumn colNoOfSeats;
    public TableColumn colStatus;
    public TableColumn colPrice;
    public JFXTextField txtPrice;
    public Label countTrain;

    public void initialize(){
        setCellValueFactory();
        loadAllTrains();
    }

    public void btnOnActionBack(ActionEvent actionEvent) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/main_dashboard_form.fxml")));
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        String trainId = txtID.getText();
        String type = txtType.getText();
        String noOfSeats = txtNoOfSeats.getText();
        String status = txtStatus.getText();
        String ticketPrice = txtPrice.getText();

        var dto = new TrainDto(trainId, type, noOfSeats, status, ticketPrice);

        var model = new TrainModel();
        try {
            boolean isSaved = model.addTrain(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Train saved!").show();
                clearFields();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String trainId = txtID.getText();
        String type = txtType.getText();
        String noOfSeats = txtNoOfSeats.getText();
        String status = txtStatus.getText();
        String ticketPrice = txtPrice.getText();

        var dto = new TrainDto(trainId, type, noOfSeats, status, ticketPrice);

        var model = new TrainModel();
        try {
            boolean isUpdated = model.updateTrain(dto);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Train updated!").show();
                clearFields();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String trainId = txtID.getText();

        var model = new TrainModel();
        try {
            boolean isDeleted = model.deleteTrain(trainId);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Train deleted!").show();
                clearFields();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnLoadOnAction(ActionEvent actionEvent) {
        String trainId = txtID.getText();

        var model = new TrainModel();
        try {
            TrainDto dto = model.searchTrain(trainId);
            if (dto != null) {
                txtID.setText(dto.getTrainId());
                txtType.setText(dto.getType());
                txtNoOfSeats.setText(dto.getNoOfSeats());
                txtStatus.setText(dto.getStatus());
                txtPrice.setText(dto.getTicketPrice());
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void loadAllTrains() {
        var model = new TrainModel();

        ObservableList<TrainTm> obList = FXCollections.observableArrayList();
        try {
            List<TrainDto> dtoList = model.getAllTrain();

            for (TrainDto dto : dtoList) {
                obList.add(
                        new TrainTm(
                                dto.getTrainId(),
                                dto.getType(),
                                dto.getNoOfSeats(),
                                dto.getStatus(),
                                dto.getTicketPrice()
                        )
                );
            }
            tblTrains.setItems(obList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colTrainID.setCellValueFactory(new PropertyValueFactory<>("trainId"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colNoOfSeats.setCellValueFactory(new PropertyValueFactory<>("noOfSeats"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("ticketPrice"));

    }

    private void clearFields() {
        txtID.clear();
        txtNoOfSeats.clear();
        txtType.clear();
        txtStatus.clear();
    }


}
