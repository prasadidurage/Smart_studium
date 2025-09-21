package lk.ijse.b72.finalproject.back_end.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "stadium")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Stadium {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    private String imageURL;

    @Column(nullable = false)
    private String name;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column
    private String location;

    @Column
    private String description;

    @Column(name = "email")
    private String email;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "stadium", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MatchRegistation> matchRegistationList;
}
