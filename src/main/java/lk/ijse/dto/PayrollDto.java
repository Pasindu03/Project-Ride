package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayrollDto implements Serializable {
    private String employeeId;
    private String salaryId;
    private String month;
    private String date;
    private String status;
    private String amount;
}
