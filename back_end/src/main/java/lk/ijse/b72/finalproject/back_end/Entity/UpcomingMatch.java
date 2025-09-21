package lk.ijse.b72.finalproject.back_end.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "upcoming_match")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UpcomingMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Column
    private String genre;

    @Column
    private String team;

    @Column(name = "duration_minutes")
    private String durationMinutes;

    @Column(name = "date")
    private String date;

    @Column(name = "match_time", nullable = false)
    private LocalTime matchTime;

    @Column
    private String cast;

    @Column( name = "film_photo")
    private String imageUrl;

}
