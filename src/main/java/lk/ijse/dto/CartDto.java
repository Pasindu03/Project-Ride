package lk.ijse.dto;

import java.io.Serializable;

public class CartDto implements Serializable {
    private String trainId;
    private String type;
    private int noOfSeats;
    private double ticketPrice;
    private double total;

    public CartDto() {
    }

    public CartDto(String trainId) {
        this.trainId = trainId;
    }

    public CartDto(String trainId, String type, int noOfSeats, double ticketPrice, double total) {
        this.trainId = trainId;
        this.type = type;
        this.noOfSeats = noOfSeats;
        this.ticketPrice = ticketPrice;
        this.total = total;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "CartDto{" +
                "trainId='" + trainId + '\'' +
                ", type='" + type + '\'' +
                ", noOfSeats=" + noOfSeats +
                ", ticketPrice=" + ticketPrice +
                ", total=" + total +
                '}';
    }
}
