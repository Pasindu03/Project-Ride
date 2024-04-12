package lk.ijse.Tm;

import org.jetbrains.annotations.NotNull;

public class ScheduleTm implements Comparable<ScheduleTm>{
    private String scheduleId;
    private String trainId;
    private String departure;
    private String arrival;
    private String departureTime;
    private String arrivalTime;

    public ScheduleTm() {
    }

    public ScheduleTm(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public ScheduleTm(String scheduleId, String trainId, String departure, String arrival, String departureTime, String arrivalTime) {
        this.scheduleId = scheduleId;
        this.trainId = trainId;
        this.departure = departure;
        this.arrival = arrival;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return "ScheduleTm{" +
                "scheduleId='" + scheduleId + '\'' +
                ", trainId='" + trainId + '\'' +
                ", departure='" + departure + '\'' +
                ", arrival='" + arrival + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                '}';
    }

    @Override
    public int compareTo(@NotNull ScheduleTm o) {
        return scheduleId.compareTo(o.getScheduleId());
    }
}
