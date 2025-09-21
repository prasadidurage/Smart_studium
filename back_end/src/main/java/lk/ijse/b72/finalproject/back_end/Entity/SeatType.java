package lk.ijse.b72.finalproject.back_end.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "seat_types")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class SeatType {
    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String type;

    @Column
    private String description;

    @Column
    private Integer quantity;

    @Column
    private Double price;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "seatType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seats> seats;

}
