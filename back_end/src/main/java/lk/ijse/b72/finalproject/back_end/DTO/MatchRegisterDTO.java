package lk.ijse.b72.finalproject.back_end.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchRegisterDTO {
    private Long id;
    private Long stadiumId;
    private Long matchId;
    private Long timeTableId;
}
