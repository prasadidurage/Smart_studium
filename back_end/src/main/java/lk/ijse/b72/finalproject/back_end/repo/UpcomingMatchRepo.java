package lk.ijse.b72.finalproject.back_end.repo;

import lk.ijse.b72.finalproject.back_end.Entity.UpcomingMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UpcomingMatchRepo extends JpaRepository<UpcomingMatch, UUID> {
}
