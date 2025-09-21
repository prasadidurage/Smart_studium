package lk.ijse.b72.finalproject.back_end.DTO;

import lk.ijse.b72.finalproject.back_end.Entity.Stadium;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatDTO {
    private Long id;
    private String rowLatter;
    private Integer seatNumber;
    private boolean isAvailable;
    private SeatTypeDTO seatType;
    private Stadium stadium;
}
