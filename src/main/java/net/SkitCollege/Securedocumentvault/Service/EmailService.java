package net.SkitCollege.Securedocumentvault.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javamailSender;
    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    public void sendEmail(String to, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
//            message.setFrom("rakhiparwani54@gmail.com");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
//            message.setReplyTo("parwanidinesh79@gmail.com");
//            System.out.println("Sending email to: " + to);

            javamailSender.send(message);

//            System.out.println("Email sent successfully!");
        } catch(Exception e) {
            log.error("Exception while sending email", e);
        }
    }
}
