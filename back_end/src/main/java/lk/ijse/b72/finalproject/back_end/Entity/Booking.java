package lk.ijse.b72.finalproject.back_end.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Entity
@Table(name = "booking")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Booking {
    @Id
    private Long id;

    @Column(name = "booking_date", nullable = false)
    private LocalDate bookingDate;

    @Column(name = "location")
    private String studioName;

    @Version
    private Long version;

    @Column(name = "time")
    private String time;

    @Column(name = "seatNumber")
    private String seatType;

    @Column(name = "User_email")
    private String email;
}
