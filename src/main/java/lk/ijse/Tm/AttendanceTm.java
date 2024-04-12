package lk.ijse.Tm;

public class AttendanceTm implements Comparable<AttendanceTm>{
    private String attendanceId;
    private String empId;
    private String month;
    private String date;
    private String status;

    public AttendanceTm() {
    }

    public AttendanceTm(String attendanceId) {
        this.attendanceId = attendanceId;
    }

    public AttendanceTm(String attendanceId, String empId, String month, String date, String status) {
        this.attendanceId = attendanceId;
        this.empId = empId;
        this.month = month;
        this.date = date;
        this.status = status;
    }

    @Override
    public String toString() {
        return "AttendanceTm{" +
                "attendanceId='" + attendanceId + '\'' +
                ", empId='" + empId + '\'' +
                ", month='" + month + '\'' +
                ", date='" + date + '\'' +
                ", status='" + status + '\'' +
                '}';
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
    public int compareTo(AttendanceTm o) {
        return attendanceId.compareTo(o.attendanceId());
    }

    private String attendanceId() {
        return attendanceId;
    }
}
