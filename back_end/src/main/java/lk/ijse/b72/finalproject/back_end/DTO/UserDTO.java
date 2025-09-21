package lk.ijse.b72.finalproject.back_end.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private UUID userId;
    private String name;
    private String email;
    private String contact;
    private String password;
    private String role;
}