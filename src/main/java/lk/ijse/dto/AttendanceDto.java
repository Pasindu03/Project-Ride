package lk.ijse.dto;

import java.io.Serializable;

public class AttendanceDto implements Serializable {
    private String attendanceId;
    private String empId;
    private String month;
    private String date;
    private String status;

    public AttendanceDto(String attendanceId) {
        this.attendanceId = attendanceId;
    }

    public AttendanceDto(String attendanceId, String empId, String month, String date, String status) {
        this.attendanceId = attendanceId;
        this.empId = empId;
        this.month = month;
        this.date = date;
        this.status = status;
    }

    public String getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(String attendanceId) {
        this.attendanceId = attendanceId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AttendanceDto{" +
                "attendanceId='" + attendanceId + '\'' +
                ", empId='" + empId + '\'' +
                ", month='" + month + '\'' +
                ", date='" + date + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
