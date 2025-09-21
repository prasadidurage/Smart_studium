package lk.ijse.b72.finalproject.back_end.repo;

import lk.ijse.b72.finalproject.back_end.Entity.Seats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepo extends JpaRepository<Seats,Long> {


}
