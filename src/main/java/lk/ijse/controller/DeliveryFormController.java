package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.Tm.DeliveryTm;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.*;
import lk.ijse.model.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DeliveryFormController {

        @FXML
        private AnchorPane root;

        @FXML
        private Label lblDeliveryID;

        @FXML
        private JFXComboBox<String> cmbPassengerId;

        @FXML
        private JFXComboBox<String> cmbTrainId;

        @FXML
        private TextField txtWeight;

        @FXML
        private TableView<DeliveryTm> tblOrderCart;

        @FXML
        private TableColumn<?, ?> colTrainId;

        @FXML
        private TableColumn<?, ?> colWeight;

        @FXML
        private TableColumn<?, ?> colUnitPrice;

        @FXML
        private TableColumn<?, ?> colTotal;

        @FXML
        private TableColumn<?, ?> colAction;

        @FXML
        private JFXButton btnAddToCart;

        @FXML
        private Label lblNetTotal;

        @FXML
        private Label lblDate;

        @FXML
        private Label passengerName;
        private PassengerModel passengerModel = new PassengerModel();
        private TrainModel trainModel = new TrainModel();
        private DeliveryModel deliveryModel = new DeliveryModel();
        private PlaceDeliveryModel placeDeliveryModel = new PlaceDeliveryModel();
        private ObservableList<DeliveryTm> obList = FXCollections.observableArrayList();

        public void initialize() {
            setCellValueFactory();
            generateNewId();
            setDate();
            loadPassengerId();
            loadTrainId();
        }

    private void loadTrainId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<TrainDto> itemDtos = TrainModel.getAllTrain();

            for (TrainDto dto : itemDtos) {
                obList.add(dto.getTrainId());
            }
            cmbTrainId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadPassengerId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<PassengerDto> passengerDtos = passengerModel.getAllPassengers();

            for (PassengerDto passengerDto : passengerDtos) {
                obList.add(passengerDto.getId());
            }
            cmbPassengerId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void generateNewId() {
        try {
            String id = DeliveryModel.generateNextOrderId();
            lblDeliveryID.setText(id);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setDate() {
        lblDate.setText(String.valueOf(LocalDate.now()));
    }

    private void setCellValueFactory() {
        colTrainId.setCellValueFactory(new PropertyValueFactory<>("trainId"));
        colWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));

    }

    @FXML
    void TrainOnAction(ActionEvent event) {String code = cmbTrainId.getValue();}

    private void setRemoveButtonAction(Button btn, String trainId) {
        btn.setOnAction(event -> {
            for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
                DeliveryTm tm = obList.get(i);

                if (tm.getTrainId().equals(trainId)) {
                    tblOrderCart.getItems().remove(i);
                    break;
                }
            }
        });
    }

        @FXML
        void btnAddToCartOnAction(ActionEvent event) {
            String trainId = cmbTrainId.getValue();
            LocalDate date = LocalDate.parse(lblDate.getText());
            double weight = Double.parseDouble(txtWeight.getText());
            double price = calculateWeightPrice(Double.parseDouble(txtWeight.getText()));
            double price1 = determinePrice(Double.parseDouble(txtWeight.getText()));

            double total = price;
            Button btn = new Button("Remove");
            setRemoveButtonAction(btn, trainId);
            
            if (!obList.isEmpty()) {
                for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
                    if (colTrainId.getCellData(i).equals(trainId)) {
                        obList.get(i).setTotal(total + obList.get(i).getTotal());
                        
                        calculateTotal();
                        tblOrderCart.refresh();
                        return;
                    }
                }
            }

            var tm = new DeliveryTm(trainId, date, weight, price1, total, btn);

            obList.add(tm);

            tblOrderCart.setItems(obList);
            txtWeight.clear();
        }

        private double calculateWeightPrice(double weight) {
            double price1 = 0;
            if(weight <= 1.00) {
                price1 = 100 * weight;

            } else if ((1.00 > weight) && (weight <= 4.00)) {
                price1 = 200 * weight;
            } else if ((4.00 < weight) && (weight <= 10.00)) {
                price1 = 300 * weight;
            } else {
                price1 = 400 * weight;
            }
            return price1;
        }

        private double determinePrice(double weight) {
            double price = 0;
            if(weight <= 1.00) {
                price = 100;
            } else if ((1.00 > weight) && (weight <= 4.00)) {
                price = 200;
            } else if ((4.00 < weight) && (weight <= 10.00)) {
                price = 300;
            } else {
                price = 400;
            }
            return price;
        }
    private void calculateTotal() {
        double total = 0;
        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            DeliveryTm tm = obList.get(i);
            total += tm.getTotal();
        }
    }

    @FXML
        void btnBackOnAction(ActionEvent event) throws IOException {
            this.root.getChildren().clear();
            this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/main_dashboard_form.fxml")));
        }

        @FXML
        void btnCheckSchedule(ActionEvent event) throws IOException {
            Parent anchorPane = FXMLLoader.load(getClass().getResource("/view/schedule_form.fxml"));
            Scene scene = new Scene(anchorPane);

            Stage stage = new Stage();
            stage.setTitle("Schedule Viewer");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        }

        @FXML
        void btnNewPassengerOnAction(ActionEvent event) throws IOException {
            Parent anchorPane = FXMLLoader.load(getClass().getResource("/view/passenger_add_form.fxml"));
            Scene scene = new Scene(anchorPane);

            Stage stage = new Stage();
            stage.setTitle("Passenger Manage");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        }

        @FXML
        void btnPlaceOrderOnAction(ActionEvent event) {
            String id = lblDeliveryID.getText();
            String passengerId = cmbPassengerId.getValue();
            LocalDate date = LocalDate.parse(lblDate.getText());

            List<CartDto2> tmList = new ArrayList<>();

            for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
                DeliveryTm tm = obList.get(i);
                CartDto2 dto = new CartDto2(id, tm.getTrainId(), tm.getWeight(), tm.getPrice());
                tmList.add(dto);
            }

            System.out.println("Place Delivery form controller : " + tmList);

            var placeDeliveryDto = new DeliveryDto(id, passengerId, date, tmList);

            try {
                boolean isSuccessful = PlaceDeliveryModel.placeDelivery(placeDeliveryDto);
                if (isSuccessful) {
                    new Alert(Alert.AlertType.INFORMATION, "Delivery Placed Successfully").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        @FXML
        void passengerOnAction(ActionEvent event) {
            String id = cmbPassengerId.getValue();

            try {
                PassengerDto dto = passengerModel.searchPassenger(id);
                passengerName.setText(dto.getName());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        @FXML
        void txtWeightOnAction(ActionEvent event) {
            btnAddToCartOnAction(event);
        }


    public void btnPrintBillOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        JasperDesign jasDesign = JRXmlLoader.load("src/main/resources/lk.ijse/reports/delivery.jrxml");
        JasperReport jasReport = JasperCompileManager.compileReport(jasDesign);
        JasperPrint jasPrint = JasperFillManager.fillReport(jasReport, null, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasPrint, false);
    }
}
