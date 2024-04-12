package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDto implements Serializable {
    private String deliveryId;
    private String passengerId;
    private LocalDate Date;
    private List<CartDto2> cartDto2 = new ArrayList<>();
}
