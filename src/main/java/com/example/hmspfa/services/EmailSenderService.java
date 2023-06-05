package com.example.hmspfa.services;

public interface EmailSenderService {
    void sendEmail(String toEmail, String subject, String body);
}
