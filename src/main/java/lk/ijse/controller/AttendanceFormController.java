package lk.ijse.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Tm.AttendanceTm;
import lk.ijse.bo.custom.AttendanceBO;
import lk.ijse.dto.AttendanceDto;
import lk.ijse.factory.BOFactory;
import lk.ijse.model.AttendanceModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AttendanceFormController {

    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField txtEmployeeID;

    @FXML
    private JFXTextField txtMonth;

    @FXML
    private JFXTextField txtAttendanceID;

    @FXML
    private JFXTextField txtDate;

    @FXML
    private JFXTextField txtStatus;

    @FXML
    private TableView<AttendanceTm> tblAttendance;

    @FXML
    private TableColumn<?, ?> colAttendanceId;
    @FXML
    public TableColumn colEmployeeID;

    @FXML
    private TableColumn<?, ?> colMonth;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colStatus;

    AttendanceBO bo = (AttendanceBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.ATTENDANCE);

    @FXML
    void btnOnActionBack(ActionEvent event) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/main_dashboard_form.fxml")));
    }

    public void initialize() {
        setCellValueFactory();
        loadAllAttendance();
        clearFields();
    }

    private void clearFields() {
        txtAttendanceID.setText("");
        txtEmployeeID.setText("");
        txtDate.setText("");
        txtMonth.setText("");
        txtStatus.setText("");
    }

    private void setCellValueFactory() {
        colAttendanceId.setCellValueFactory(new PropertyValueFactory<>("attendanceId"));
        colEmployeeID.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colMonth.setCellValueFactory(new PropertyValueFactory<>("month"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

    }

    private void loadAllAttendance() {
        tblAttendance.getItems().clear();
        try{
            ArrayList<AttendanceDto> allAttendance = bo.getAll();

            for(AttendanceDto a : allAttendance){
                tblAttendance.getItems().add(new AttendanceTm(a.getAttendanceId(), a.getEmpId(), a.getMonth(), a.getDate(), a.getStatus()));
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnOnActionClear(ActionEvent event) {
        clearFields();
    }
    boolean existAttendance(String id) throws SQLException, ClassNotFoundException {
        return bo.existRecord(id);
    }

    private String generateNewId() {
        try {
            return bo.generateNewId();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (tblAttendance.getItems().isEmpty()) {
            return "A001";
        } else {
            String id = getLastCustomerId();
            int newCustomerId = Integer.parseInt(id.replace("A", "")) + 1;
            return String.format("A%03d", newCustomerId);
        }
    }
    private String getLastCustomerId() {
        List<AttendanceTm> tempAttendanceList = new ArrayList<>(tblAttendance.getItems());
        Collections.sort(tempAttendanceList);
        return tempAttendanceList.get(tempAttendanceList.size() - 1).getAttendanceId();
    }

    @FXML
    void btnOnActionAdd(ActionEvent event) {
        String attId = txtAttendanceID.getText();
        String id = txtEmployeeID.getText();
        String month = txtMonth.getText();
        String date = txtDate.getText();
        String status = txtStatus.getText();

        try {
            if (existAttendance(id)) {
                new Alert(Alert.AlertType.ERROR, id + " already exists").show();
            }

            AttendanceDto attendanceDto = new AttendanceDto(attId, id, month, date, status);
            bo.save(attendanceDto);

            tblAttendance.getItems().add(new AttendanceTm(attId, id, month, date, status));
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to save the Attendance" + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        AttendanceTm tm = tblAttendance.getSelectionModel().getSelectedItem();
        tm.setAttendanceId(attId);
        tm.setEmpId(id);
        tblAttendance.refresh();
    }

    @FXML
    void btnOnActionDelete(ActionEvent event) {
        String id = txtAttendanceID.getText();

        var model = new AttendanceModel();

        try {
            if (!existAttendance(id)) {
                new Alert(Alert.AlertType.ERROR, "There is no such customer associated with the id " + id).show();
            }

            AttendanceDto dto = new AttendanceDto(id);
            bo.delete(dto);

            tblAttendance.getItems().remove(tblAttendance.getSelectionModel().getSelectedItem());
            tblAttendance.getSelectionModel().clearSelection();
            clearFields();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the customer " + id).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnOnActionUpdate(ActionEvent event) {
        String attId = txtAttendanceID.getText();
        String id = txtEmployeeID.getText();
        String month = txtMonth.getText();
        String date = txtDate.getText();
        String status = txtStatus.getText();

        var dto = new AttendanceDto(attId, id, month, date, status);

        var model = new AttendanceModel();

        try {
            if (!existAttendance(id)) {
                new Alert(Alert.AlertType.ERROR, "There is no such customer associated with the id " + id).show();
            }
            AttendanceDto attendanceDto = new AttendanceDto(attId, id, month, date, status);
            bo.update(attendanceDto);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to update the customer " + id + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        AttendanceTm tm = tblAttendance.getSelectionModel().getSelectedItem();
        tm.setAttendanceId(attId);
        tm.setEmpId(id);
        tblAttendance.refresh();
    }

    @FXML
    void tblPassengerOnMouseClicked(MouseEvent event) {
    }

    @FXML
    void txtOnActionSearch(ActionEvent event) {
        String attId = txtAttendanceID.getText();

        var model = new AttendanceModel();

        try {
            AttendanceDto attendance = model.searchAttendance(attId);
            if (attendance != null) {
                txtEmployeeID.setText(attendance.getEmpId());
                txtMonth.setText(attendance.getMonth());
                txtDate.setText(attendance.getDate());
                txtStatus.setText(attendance.getStatus());
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

}

