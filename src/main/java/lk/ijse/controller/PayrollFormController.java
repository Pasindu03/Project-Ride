package lk.ijse.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Tm.PayrollTm;
import lk.ijse.dto.PayrollDto;
import lk.ijse.model.PayrollModel;

import java.io.IOException;
import java.util.List;

public class PayrollFormController {

    public AnchorPane root;
    @FXML
    public TableColumn colAmount;

    @FXML
    private JFXTextField txtEmployeeID;

    @FXML
    private JFXTextField txtMonth;

    @FXML
    private JFXTextField txtSalaryID;

    @FXML
    private JFXTextField txtDate;

    @FXML
    private JFXTextField txtStatus;

    @FXML
    private TableView<PayrollTm> tblPassenger;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colSalaryID;

    @FXML
    private TableColumn<?, ?> colMonth;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private JFXTextField txtAmount;

    @FXML
    void btnOnActionBack(ActionEvent event) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/main_dashboard_form.fxml")));
    }

    @FXML
    void btnOnActionAdd(ActionEvent event) {
        String id = txtEmployeeID.getText();
        String salaryId = txtSalaryID.getText();
        String month = txtMonth.getText();
        String date = txtDate.getText();
        String status = txtStatus.getText();
        String amount = txtAmount.getText();

        var dto = new PayrollDto(id, salaryId, month, date, status, amount);

        var model = new PayrollModel();
        try {
            boolean isSaved = model.addPayroll(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Payroll saved!").show();
                clearFields();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnOnActionDelete(ActionEvent event) {
        String salaryID = txtSalaryID.getText();

        var model = new PayrollModel();
        try {
            boolean isDeleted = model.deletePayroll(salaryID);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Train deleted!").show();
                clearFields();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnOnActionUpdate(ActionEvent event) {
        String id = txtEmployeeID.getText();
        String salaryId = txtSalaryID.getText();
        String month = txtMonth.getText();
        String date = txtDate.getText();
        String status = txtStatus.getText();
        String amount = txtAmount.getText();

        var dto = new PayrollDto(id, salaryId, month, date, status, amount);

        var model = new PayrollModel();
        try {
            boolean isUpdated = model.updatePayroll(dto);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Payroll Updated!").show();
                clearFields();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void txtOnActionSearch(ActionEvent event) {

    }

    @FXML
    void tblPassengerOnMouseClicked(MouseEvent mouseEvent) {
    }

    private void loadAllId(){
        var model = new PayrollModel();

        ObservableList<PayrollTm> observableList = FXCollections.observableArrayList();
        try{
            List<PayrollDto> dtoList = model.getAllPayroll();

            for (PayrollDto dto : dtoList){
                observableList.add(
                        new PayrollTm(
                                dto.getEmployeeId(),
                                dto.getSalaryId(),
                                dto.getMonth(),
                                dto.getDate(),
                                dto.getStatus(),
                                dto.getAmount()
                        )
                );
            }
            tblPassenger.setItems(observableList);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void initialize(){
        loadAllId();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colSalaryID.setCellValueFactory(new PropertyValueFactory<>("salaryId"));
        colMonth.setCellValueFactory(new PropertyValueFactory<>("month"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }

    @FXML
    void btnOnActionClear(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtEmployeeID.clear();
        txtSalaryID.clear();
        txtMonth.clear();
        txtDate.clear();
        txtStatus.clear();
        txtAmount.clear();
    }
}

