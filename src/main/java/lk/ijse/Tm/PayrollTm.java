package lk.ijse.Tm;

public class PayrollTm implements Comparable<PayrollTm>{
    private String employeeId;
    private String salaryId;
    private String month;
    private String date;
    private String status;
    private String amount;

    public PayrollTm() {
    }

    public PayrollTm(String salaryId) {
        this.salaryId = salaryId;
    }

    public PayrollTm(String employeeId, String salaryId, String month, String date, String status, String amount) {
        this.employeeId = employeeId;
        this.salaryId = salaryId;
        this.month = month;
        this.date = date;
        this.status = status;
        this.amount = amount;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(String salaryId) {
        this.salaryId = salaryId;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public int compareTo(PayrollTm o) {
        return salaryId.compareTo(o.getSalaryId());
    }
}
