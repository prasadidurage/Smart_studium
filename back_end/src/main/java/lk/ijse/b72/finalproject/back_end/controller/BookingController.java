package lk.ijse.b72.finalproject.back_end.controller;

//import jakarta.mail.MessagingException;

import lk.ijse.b72.finalproject.back_end.DTO.BookingDTO;
import lk.ijse.b72.finalproject.back_end.service.BookingService;
import lk.ijse.b72.finalproject.back_end.util.ResponceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/booking")
@CrossOrigin
public class BookingController {



    @Autowired
    private BookingService timeTableService;



 public ResponceUtil getFilHall(@RequestBody BookingDTO timeTableDTO) {
//        timeTableService.save(timeTableDTO);
//
//
//        String userEmail = timeTableDTO.getEmail();
//        LocalDate bookingDate = timeTableDTO.getBookingDate();
//        String time = timeTableDTO.getTime();
//        String film = timeTableDTO.getFilm();
//        String filmHall = timeTableDTO.getFilmHall();
//        String seat = timeTableDTO.getSeat();

//        try {
//
//            emailService.sendBookingConfirmationEmail(
//                    userEmail,
//                    "Your Booking is Confirmed – SCOPE CINEMA 🍿",
//                    "<html>" +
//                            "<body style='font-family: Arial, sans-serif;'>" +
//                            "<h2 style='color: #0d253f;'>Your Movie Booking is Confirmed! 🎉</h2>" +
//                            "<p>Hi there,</p>" +
//                            "<p>Thank you for choosing <strong>Our Cinema</strong>! 🍿</p>" +
//                            "<p>Your movie booking has been confirmed successfully. Here are your details:</p>" +
//                            "<p>🎬 <strong>Movie:</strong> " + film + "<br>" +
//                            "📅 <strong>Date:</strong> " + bookingDate + "<br>" +
//                            "⏰ <strong>Time:</strong> " + time + "<br>" +
//                            "🏛️ <strong>Theater:</strong> " + filmHall + "<br>" +
//                            "💺 <strong>Seat Number:</strong> " + seat + "<br>" +
//                            "📞 <strong>Support:</strong> 011-2345678</p>" +
//
//
//                            "<p>Please arrive at least 15 minutes before the show starts.</p>" +
//
//
//
//                            "<h3>🍿 Get Ready for the Show</h3>" +
//                            "<p>If you have any questions or need assistance, feel free to contact us anytime!</p>" +
//
//                            "<p>Enjoy the movie! 🎬</p>" +
//                            "<p><strong>Our Cinema</strong><br>" +
//                            "📍 Cinema Complex, Main Street<br>" +
//                            "📞 011-2345678</p>" +
//                            "</body>" +
//                            "</html>"
//            );
//        }catch (MessagingException e) {
//            e.printStackTrace();
//            System.out.println("email not send ");
//        }
//
//
//
//        return new ResponceUtil( 201," booking is Saved" ,null);
//
//
//
//
//    }
//
//
//    @GetMapping("getAll")
//
//  //  @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
//    private ResponceUtil getAllFilmHall(){
//        return  new ResponceUtil(200,"success",timeTableService.getAll());
//    }
//
//
//    @DeleteMapping(path = "delete/{id}")
//    public ResponceUtil deleteCustomer(@PathVariable(value = "id") Long id){
//        timeTableService.delete(id);
//        return new ResponceUtil(200,"booking deleted" ,null);
//    }
//
//
//

//
//    @PutMapping(path = "update")
//    public  ResponceUtil update(@RequestBody  BookingDTO timeTableDTO){
//        timeTableService.update(timeTableDTO);
//        return new ResponceUtil( 201,"booking is updated" ,null);
     return null;

    }



}
