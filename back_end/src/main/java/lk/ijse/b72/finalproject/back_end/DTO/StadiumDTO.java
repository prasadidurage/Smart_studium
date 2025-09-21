package lk.ijse.b72.finalproject.back_end.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StadiumDTO<T> implements Serializable {
    private UUID id;
    private String imageURL;
    private String name;
    private String contactNumber;
    private String location;
    private String description;
    private String email;

}
