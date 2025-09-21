package lk.ijse.b72.finalproject.back_end.service;

import jakarta.mail.MessagingException;

public interface EmailService {
    void sendBookingConfirmationEmail(String to, String subject, String body) throws MessagingException;
}
