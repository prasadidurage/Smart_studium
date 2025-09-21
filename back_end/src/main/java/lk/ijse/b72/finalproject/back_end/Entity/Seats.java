package lk.ijse.b72.finalproject.back_end.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "seats")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Seats {
    @Id
    private Long id;

    @Column(nullable = false)
    private String rowLatter;

    @Column(name = "seatNumber", nullable = false)
    private Integer seatNumber;


    @Column(nullable = false)
    private boolean isAvailable;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "seat_type_id", nullable = false)  // This should be nullable=false
    private SeatType seatType;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "stadium_id", nullable = false)  // This should be nullable=false
    private Stadium stadium;

}
