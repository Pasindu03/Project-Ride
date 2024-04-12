package lk.ijse.Tm;

public class EmployeeTm implements Comparable<EmployeeTm>{
    private String empId;
    private String name;
    private String age;
    private String gender;
    private String address;
    private String contact;

    public EmployeeTm() {
    }

    public EmployeeTm(String empId) {
        this.empId = empId;
    }

    public EmployeeTm(String empId, String name, String age, String gender, String address, String contact) {
        this.empId = empId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "EmployeeTm{" +
                "empId='" + empId + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public int compareTo(EmployeeTm o) {
        return empId.compareTo(o.getEmpId());
    }
}
