package lk.ijse.Tm;

import org.jetbrains.annotations.NotNull;

public class TrainTm implements Comparable<TrainTm>{
    private String trainId;
    private String type;
    private String noOfSeats;
    private String status;
    private String ticketPrice;

    public TrainTm() {
    }

    public TrainTm(String trainId) {
        this.trainId = trainId;
    }

    public TrainTm(String trainId, String type, String noOfSeats, String status, String ticketPrice) {
        this.trainId = trainId;
        this.type = type;
        this.noOfSeats = noOfSeats;
        this.status = status;
        this.ticketPrice = ticketPrice;
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

    public String getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(String noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(String ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String toString() {
        return "TrainTm{" +
                "trainId='" + trainId + '\'' +
                ", type='" + type + '\'' +
                ", noOfSeats='" + noOfSeats + '\'' +
                ", status='" + status + '\'' +
                ", ticketPrice='" + ticketPrice + '\'' +
                '}';
    }

    @Override
    public int compareTo(@NotNull TrainTm o) {
        return trainId.compareTo(o.getTrainId());
    }
}
