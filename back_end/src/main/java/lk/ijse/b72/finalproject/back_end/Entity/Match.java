package lk.ijse.b72.finalproject.back_end.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Entity
@Table(name = "matches")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;       // Match title (e.g., "Sri Lanka vs India")

    @Column(length = 1000)
    private String description; // Short description of the match

    @Column
    private String genre;       // Type (Cricket, Football, Rugby...)

    @Column
    private String teamA;       // First team

    @Column
    private String teamB;       // Second team

    @Column(name = "duration_minutes")
    private int durationMinutes;

    @Column(name = "match_date")
    private LocalDate matchDate;


    @Column
    private String stadium;     // Stadium/Location name


    @Column( name = "match_photo")
    private String imageUrl;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "match", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = false)
    private List<MatchRegistation> matchRegistrationList;

}
