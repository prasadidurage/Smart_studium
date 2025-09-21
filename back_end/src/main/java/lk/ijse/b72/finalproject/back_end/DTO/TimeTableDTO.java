package lk.ijse.b72.finalproject.back_end.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeTableDTO {
    private Long id;
    private String description;
    private LocalTime showTime;
    private LocalTime endTime;
}
