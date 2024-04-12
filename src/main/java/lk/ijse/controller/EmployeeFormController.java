package lk.ijse.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Tm.EmployeeTm;
import lk.ijse.dto.EmployeeDto;
import lk.ijse.model.EmployeeModel;

import java.io.IOException;
import java.util.List;

public class EmployeeFormController {
    public AnchorPane root;
    public JFXTextField txtId;
    public JFXTextField txtAge;
    public JFXTextField txtName;
    public JFXTextField txtGender;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public TableView tblEmployee;
    public TableColumn colID;
    public TableColumn colName;
    public TableColumn colAge;
    public TableColumn colGender;
    public TableColumn colAddress;
    public TableColumn colContact;

    public void btnOnActionBack(ActionEvent actionEvent) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/main_dashboard_form.fxml")));
    }
    
    public void initialize(){
        loadAllEmployees();
        clearFields();
        setCellValueFactory();
    }

    private void loadAllEmployees() {
        var model = new EmployeeModel();

        ObservableList<EmployeeTm> obList = FXCollections.observableArrayList();
        try {
            List<EmployeeDto> employeeDtos = model.getAllEmployees();

            for (EmployeeDto dto : employeeDtos) {
                obList.add(
                        new EmployeeTm(
                                dto.getEmpId(),
                                dto.getName(),
                                dto.getAge(),
                                dto.getGender(),
                                dto.getAddress(),
                                dto.getContact()
                        )
                );
            }
            tblEmployee.setItems(obList);
        } catch (Exception e) {
            throw new RuntimeException(e);
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

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
    }

    public void txtOnActionSearch(ActionEvent actionEvent) {
    }

    public void btnOnActionAdd(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String age = txtAge.getText();
        String gender = txtGender.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();

        var dto = new EmployeeDto(id, name, age, gender, address, contact);

        var model = new EmployeeModel();
        try {
            boolean isSaved = model.addEmployee(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee saved!").show();
                clearFields();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnOnActionUpdate(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String age = txtAge.getText();
        String gender = txtGender.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();

        var dto = new EmployeeDto(id, name, age, gender, address, contact);

        var model = new EmployeeModel();
        try {
            boolean isUpdated = model.updateEmployee(dto);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee updated!").show();
                clearFields();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnOnActionClear(ActionEvent actionEvent) {
        clearFields();
    }

    public void btnOnActionDelete(ActionEvent actionEvent) {
        String id = txtId.getText();

        var model = new EmployeeModel();
        try {
            boolean isDeleted = model.deleteEmployee(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee deleted!").show();
                clearFields();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void tblEmployeeOnMouseClicked(MouseEvent mouseEvent) {
    }
}
