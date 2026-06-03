package net.SkitCollege.Securedocumentvault.Service;

import net.SkitCollege.Securedocumentvault.Entity.DocumentsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService {

    @Autowired
    private JavaMailSender mailSender;

//    public EmailNotificationService(JavaMailSender mailSender) {
//        this.mailSender = mailSender;
//    }

    // 🔹 UPDATE REMINDER
    public void sendUpdateReminder(DocumentsEntity doc) {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(doc.getOwnerEmail());
        mail.setSubject("Document Update Required");
        mail.setText(
                "Your document '" + doc.getDocumentName() +
                        "' needs to be updated.\n\nPlease login and update it."
        );

        mailSender.send(mail);
    }

    // 🔹 EXPIRY REMINDER
    public void sendExpiryReminder(DocumentsEntity doc) {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(doc.getOwnerEmail());
        mail.setSubject("Document Expiry Alert ⚠️");
        mail.setText(
                "Your document '" + doc.getDocumentName() +
                        "' is expiring on " + doc.getExpiryDate()
        );

        mailSender.send(mail);
    }
}

