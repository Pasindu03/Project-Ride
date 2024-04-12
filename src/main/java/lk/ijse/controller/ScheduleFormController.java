package lk.ijse.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Tm.ScheduleTm;
import lk.ijse.dto.ScheduleDto;
import lk.ijse.dto.TrainDto;
import lk.ijse.model.ScheduleModel;
import lk.ijse.model.TrainModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ScheduleFormController {
    public AnchorPane root;
    public JFXTextField txtStartStation;
    public JFXTextField txtEndStation;
    public TableView tblPassenger;
    public TableColumn colEndingStation;
    public TableColumn colID;
    public TableColumn colTrainId;
    public TableColumn colStartStation;
    public TableColumn colStartTime;
    public TableColumn colEndTime;
    public ComboBox ComboTrainId;
    public JFXTextField txtEndTime;
    public JFXTextField txtStartTime;
    public JFXTextField txtScheduleId;

    public void btnOnActionBack(ActionEvent actionEvent) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/main_dashboard_form.fxml")));
    }

    public void initialize() {
        setCellValueFactory();
        loadAllSchedule();
        loadTrainId();
    }

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("scheduleId"));
        colTrainId.setCellValueFactory(new PropertyValueFactory<>("trainId"));
        colStartStation.setCellValueFactory(new PropertyValueFactory<>("departure"));
        colEndingStation.setCellValueFactory(new PropertyValueFactory<>("arrival"));
        colStartTime.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
        colEndTime.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
    }

    private void loadTrainId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<TrainDto> itemDtos = TrainModel.getAllTrain();

            for (TrainDto dto : itemDtos) {
                obList.add(dto.getTrainId());
            }
            ComboTrainId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllSchedule() {
        var model = new ScheduleModel();

        ObservableList<ScheduleTm> obList = FXCollections.observableArrayList();
        try {
            List<ScheduleDto> dtoList = model.getAllSchedule();

            for (ScheduleDto dto : dtoList) {
                obList.add(new ScheduleTm(
                        dto.getScheduleId(),
                        dto.getTrainId(),
                        dto.getDeparture(),
                        dto.getArrival(),
                        dto.getDepartureTime(),
                        dto.getArrivalTime()
                ));
            }
            tblPassenger.setItems(obList);
        } catch(Exception e){
        throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        txtScheduleId.clear();
        txtEndStation.clear();
        txtStartStation.clear();
        txtStartTime.clear();
        txtEndTime.clear();
        ComboTrainId.getSelectionModel().clearSelection();
    }

    public void txtOnActionSearch(ActionEvent actionEvent) {
    }

    public void tblScheduleOnMouseClicked(MouseEvent mouseEvent) {
        String scheduleId = txtScheduleId.getText();

        var model = new ScheduleModel();
        try {
            ScheduleDto dto = model.searchSchedule(scheduleId);
            if (dto != null) {
                txtStartStation.setText(dto.getDeparture());
                txtEndStation.setText(dto.getArrival());
                txtStartTime.setText(dto.getDepartureTime());
                txtEndTime.setText(dto.getArrivalTime());
                ComboTrainId.getSelectionModel().select(dto.getTrainId());
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnOnActionAdd(ActionEvent actionEvent) {
        String scheduleId = txtScheduleId.getText();
        String trainId = ComboTrainId.getSelectionModel().getSelectedItem().toString();
        String startStation = txtStartStation.getText();
        String endStation = txtEndStation.getText();
        String startTime = txtStartTime.getText();
        String endTime = txtEndTime.getText();

        var dto = new ScheduleDto(scheduleId, trainId, startStation, endStation, startTime, endTime);

        var model = new ScheduleModel();
        try{
            boolean isAdded = model.addSchedule(dto);
            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Added").show();
                clearFields();
            }
        } catch (Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnOnActionUpdate(ActionEvent actionEvent) {
        String scheduleId = txtScheduleId.getText();
        String trainId = ComboTrainId.getSelectionModel().getSelectedItem().toString();
        String startStation = txtStartStation.getText();
        String endStation = txtEndStation.getText();
        String startTime = txtStartTime.getText();
        String endTime = txtEndTime.getText();

        var dto = new ScheduleDto(scheduleId, trainId, startStation, endStation, startTime, endTime);

        var model = new ScheduleModel();
        try{
            boolean isUpdated = model.updateSchedule(dto);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Added").show();
                clearFields();
            }
        } catch (Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnOnActionDelete(ActionEvent actionEvent) {
        String scheduleId = txtScheduleId.getText();

        var model = new ScheduleModel();
        try{
            boolean isDeleted = model.deleteSchedule(scheduleId);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
                clearFields();
            }
        } catch (Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnOnActionClear(ActionEvent actionEvent) {clearFields();}
}
