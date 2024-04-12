package lk.ijse.Tm;

import javafx.scene.control.Button;
import org.jetbrains.annotations.NotNull;

public class ReservationTm implements Comparable<ReservationTm> {
    private String trainId;
    private String type;
    private int noOfSeats;
    private double ticketPrice;
    private double tot;
    private Button btn;

    public ReservationTm(String trainId) {
        this.trainId = trainId;
    }

    public ReservationTm(String trainId, String type, int noOfSeats, double ticketPrice, double tot, Button btn) {
        this.trainId = trainId;
        this.type = type;
        this.noOfSeats = noOfSeats;
        this.ticketPrice = ticketPrice;
        this.tot = tot;
        this.btn = btn;
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public double getTot() {
        return tot;
    }

    public void setTot(double tot) {
        this.tot = tot;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "ReservationTm{" +
                "trainId='" + trainId + '\'' +
                ", type='" + type + '\'' +
                ", noOfSeats=" + noOfSeats +
                ", ticketPrice=" + ticketPrice +
                ", tot=" + tot +
                ", btn=" + btn +
                '}';
    }

    @Override
    public int compareTo(@NotNull ReservationTm o) {
        return trainId.compareTo(o.getTrainId());
    }
}
