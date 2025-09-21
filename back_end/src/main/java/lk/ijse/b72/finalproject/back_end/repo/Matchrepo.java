package lk.ijse.b72.finalproject.back_end.repo;

import lk.ijse.b72.finalproject.back_end.Entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface Matchrepo extends JpaRepository<Match, UUID> {
}
