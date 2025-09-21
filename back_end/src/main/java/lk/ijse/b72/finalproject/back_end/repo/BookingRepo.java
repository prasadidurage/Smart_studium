package lk.ijse.b72.finalproject.back_end.repo;

import  lk.ijse.b72.finalproject.back_end.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepo extends JpaRepository<Booking,Long> {
}
