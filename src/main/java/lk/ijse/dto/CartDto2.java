package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto2 implements Serializable {
    private String deliveryId;
    private String trainId;
    private double weight;
    private double price;
}
