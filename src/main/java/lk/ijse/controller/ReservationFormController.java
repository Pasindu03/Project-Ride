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
import lk.ijse.Tm.ReservationTm;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.CartDto;
import lk.ijse.dto.PassengerDto;
import lk.ijse.dto.ReservationDto;
import lk.ijse.dto.TrainDto;
import lk.ijse.model.PassengerModel;
import lk.ijse.model.PlaceReservationModel;
import lk.ijse.model.ReservationModel;
import lk.ijse.model.TrainModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationFormController {

        @FXML
        private AnchorPane root;

        @FXML
        private Label lblReservationId;

        @FXML
        private JFXComboBox<String> cmbPassengerId;

        @FXML
        private JFXComboBox<String> cmbTrainId;

        @FXML
        private TextField txtQty;

        @FXML
        private TableView<ReservationTm> tblOrderCart;

        @FXML
        private TableColumn<?, ?> colTrainId;

        @FXML
        private TableColumn<?, ?> colType;

        @FXML
        private TableColumn<?, ?> colNoOfSeats;

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
        private Label lblType;

        @FXML
        private Label lblUnitPrice;

        @FXML
        private Label lblSeatsAvailable;

        @FXML
        private Label lblDate;

        @FXML
        private Label passengerName;

        private PassengerModel passengerModel = new PassengerModel();
        private TrainModel trainModel = new TrainModel();
        private ReservationModel reservationModel = new ReservationModel();
        private PlaceReservationModel placeReservationModel = new PlaceReservationModel();
        private ObservableList<ReservationTm> obList = FXCollections.observableArrayList();
        public void initialize() {
            setCellValueFactory();
            generateNewId();
            setDate();
            loadPassengerId();
            loadTrainId();
        }

    private void setCellValueFactory() {
            colTrainId.setCellValueFactory(new PropertyValueFactory<>("trainId"));
            colType.setCellValueFactory(new PropertyValueFactory<>("type"));
            colNoOfSeats.setCellValueFactory(new PropertyValueFactory<>("noOfSeats"));
            colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("ticketPrice"));
            colTotal.setCellValueFactory(new PropertyValueFactory<>("tot"));
            colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    private void generateNewId() {
        try {
            String id = reservationModel.generateNextOrderId();
            lblReservationId.setText(id);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
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

    private void setDate() {
        lblDate.setText(String.valueOf(LocalDate.now()));
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
    void btnBackOnAction(ActionEvent event) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/main_dashboard_form.fxml")));
    }

    @FXML
    void TrainOnAction(ActionEvent event) {
        String code = cmbTrainId.getValue();

        txtQty.requestFocus();
        try {
            TrainDto dto = trainModel.searchTrain(code);
            lblType.setText(dto.getType());
            lblSeatsAvailable.setText(dto.getNoOfSeats());
            lblUnitPrice.setText(dto.getTicketPrice());
        } catch (SQLException e) {
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
    void txtQtyOnAction(ActionEvent event) {
        btnAddToCartOnAction(event);
    }

    private void calculateTotal() {
        double total = 0;
        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            ReservationTm tm = obList.get(i);
            total += tm.getTot();
        }
    }

    private void setRemoveButtonAction(Button btn, String trainId) {
        btn.setOnAction(event -> {
            for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
                ReservationTm tm = obList.get(i);

                if (tm.getTrainId().equals(trainId)) {
                    tblOrderCart.getItems().remove(i);
                    break;
                }
            }
        });
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        String id = lblReservationId.getText();
        LocalDate date = LocalDate.parse(lblDate.getText());
        String passengerId = cmbPassengerId.getValue();

        List<CartDto> tmList = new ArrayList<>();

        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            ReservationTm tm = obList.get(i);
            CartDto dto = new CartDto(tm.getTrainId(), tm.getType(), tm.getNoOfSeats(), tm.getTicketPrice(), tm.getTot());
            tmList.add(dto);
        }

        System.out.println("Place Reservation form controller : " + tmList);

        var placeReservationDto = new ReservationDto(id, date, passengerId, tmList);
        try {
            boolean isSuccessful = PlaceReservationModel.placeReservation(placeReservationDto);
            if (isSuccessful) {
                new Alert(Alert.AlertType.INFORMATION, "Reservation Placed Successfully").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        String trainId = cmbTrainId.getValue();
        String type = lblType.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double unitPrice = Double.parseDouble(lblUnitPrice.getText());
        double total = qty * unitPrice;
        Button btn = new Button("Remove");

        setRemoveButtonAction(btn, trainId);

        if (!obList.isEmpty()) {
            for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
                if (colTrainId.getCellData(i).equals(trainId)) {
                    int col_qty = (int) colNoOfSeats.getCellData(i);
                    qty += col_qty;
                    total = qty * unitPrice;

                    obList.get(i).setNoOfSeats(qty);
                    obList.get(i).setTot(total);

                    calculateTotal();
                    tblOrderCart.refresh();
                    return;
                }
            }
        }

        var tm = new ReservationTm(trainId, type, qty, unitPrice, total, btn);

        obList.add(tm);

        tblOrderCart.setItems(obList);
        calculateTotal();
        txtQty.clear();
    }

    public void btnPrintBillOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        JasperDesign jasDesign = JRXmlLoader.load("src/main/resources/lk.ijse/reports/reservation.jrxml");
        JasperReport jasReport = JasperCompileManager.compileReport(jasDesign);
        JasperPrint jasPrint = JasperFillManager.fillReport(jasReport, null, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasPrint, false);
    }

    public void btnCheckSchedule(ActionEvent actionEvent) throws IOException {
        Parent anchorPane = FXMLLoader.load(getClass().getResource("/view/schedule_form.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = new Stage();
        stage.setTitle("Schedule Viewer");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}

