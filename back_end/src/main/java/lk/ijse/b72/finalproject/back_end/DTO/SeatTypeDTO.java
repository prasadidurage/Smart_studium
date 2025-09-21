package lk.ijse.b72.finalproject.back_end.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatTypeDTO {
    private Long id;
    private String type;
    private String description;
    private Integer quantity;
    private Double price;
}

