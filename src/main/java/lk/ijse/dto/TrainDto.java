package lk.ijse.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TrainDto implements Serializable {
    private String trainId;
    private String type;
    private String noOfSeats;
    private String status;
    private String ticketPrice;
}