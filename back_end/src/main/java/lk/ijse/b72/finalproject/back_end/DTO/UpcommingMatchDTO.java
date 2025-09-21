package lk.ijse.b72.finalproject.back_end.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpcommingMatchDTO <T> implements Serializable {
    private UUID id;
    private String title;
    private String description;
    private String genre;
    private String team;
    private String durationMinutes;
    private String date;
    private LocalTime matchTime;
    private String cast;
    private  T imageUrl;
}
