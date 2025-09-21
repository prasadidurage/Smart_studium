package lk.ijse.b72.finalproject.back_end.DTO;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthDTO {
    public String email;
    public String token;
    public String role;
}
