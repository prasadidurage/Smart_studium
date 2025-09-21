package lk.ijse.b72.finalproject.back_end.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "time_table")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class TimeTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO increment DB column
    private Long id;


    @Column(name = "description" )
    private  String description;

    @Column(name = "show_time", nullable = false)
    private LocalTime showTime;


    @Column(name = "end_time")
    private LocalTime endTime;



//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "time_table_id", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<MatchRegistation> matchRegistationList;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "timeTable", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MatchRegistation> matchRegistationList;

}
