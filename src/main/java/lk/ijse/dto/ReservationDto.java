package lk.ijse.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReservationDto implements Serializable {
    private String reservationId;
    private LocalDate orderDate;
    private String passengerId;
    private List<CartDto> cartDtos = new ArrayList<>();
}
