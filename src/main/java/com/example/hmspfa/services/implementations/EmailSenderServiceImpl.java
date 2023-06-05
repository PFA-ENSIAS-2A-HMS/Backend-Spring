package com.example.hmspfa.services.implementations;

import com.example.hmspfa.services.EmailSenderService;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailSenderServiceImpl implements EmailSenderService {
   private JavaMailSender javaMailSender;

   @Override
   public void sendEmail(String toEmail, String subject, String body){
       SimpleMailMessage message = new SimpleMailMessage();
       message.setFrom("abderazaknfissi34@gmail.com");
       message.setTo(toEmail);
       message.setText(body);
       message.setSubject(subject);
       javaMailSender.send(message);
       System.out.println("#############Mail sent successfully  ##############");
   }
}
