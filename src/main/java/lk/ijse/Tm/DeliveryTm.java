package lk.ijse.Tm;

import javafx.scene.control.Button;

import java.time.LocalDate;

public class DeliveryTm implements Comparable<DeliveryTm>{
    private String TrainId;
    private LocalDate date;
    private double weight;
    private double price;
    private double total;
    private Button btn;

    public DeliveryTm(String trainId) {
        TrainId = trainId;
    }

    public DeliveryTm(String trainId, LocalDate date, double weight, double price, double total, Button btn) {
        TrainId = trainId;
        this.date = date;
        this.weight = weight;
        this.price = price;
        this.total = total;
        this.btn = btn;
    }

    public String getTrainId() {
        return TrainId;
    }

    public void setTrainId(String trainId) {
        TrainId = trainId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "DeliveryTm{" +
                "TrainId='" + TrainId + '\'' +
                ", date=" + date +
                ", weight=" + weight +
                ", price=" + price +
                ", total=" + total +
                ", btn=" + btn +
                '}';
    }

    @Override
    public int compareTo(DeliveryTm o) {
        return TrainId.compareTo(o.getTrainId());
    }
}
