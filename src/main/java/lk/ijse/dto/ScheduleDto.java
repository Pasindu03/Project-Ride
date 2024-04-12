package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDto implements Serializable {
    private String scheduleId;
    private String trainId;
    private String departure;
    private String arrival;
    private String departureTime;
    private String arrivalTime;
}
