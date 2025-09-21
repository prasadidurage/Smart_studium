package lk.ijse.b72.finalproject.back_end.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Entity
@Table(name = "match_registation")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MatchRegistation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Show එක 어느 stadium/ground එකේද
    @ManyToOne
    @JoinColumn(name = "stadium_id")
    private Stadium stadium;

    // කුමන match එකද (cricket/football)
    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    // Time slot එක
    @ManyToOne
    @JoinColumn(name = "time_table_id")
    private TimeTable timeTable;

//    // One show → many bookings
//    @OneToMany(mappedBy = "show")
//    private List<Booking> bookings;
}

