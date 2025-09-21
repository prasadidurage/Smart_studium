package lk.ijse.b72.finalproject.back_end.repo;

import lk.ijse.b72.finalproject.back_end.Entity.SeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeatTypeRepo extends JpaRepository<SeatType,Long> {

    Optional<SeatType> findById(Long id);
}
