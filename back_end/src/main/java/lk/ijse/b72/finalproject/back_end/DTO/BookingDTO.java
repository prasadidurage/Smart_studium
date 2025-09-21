package lk.ijse.b72.finalproject.back_end.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
    private Long id;
    private LocalDate bookingDate;
    private String studioName;
    private Long version;
    private String time;
    private String seatType;
    private String email;
}