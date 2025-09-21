package lk.ijse.b72.finalproject.back_end.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchDTO <T> implements Serializable {
    private UUID id;
    private String title;
    private String description;
    private String genre;
    private String teamA;
    private String teamB;
    private int durationMinutes;
    private LocalDate matchDate;
    private String stadium;
    private String imageUrl;
}