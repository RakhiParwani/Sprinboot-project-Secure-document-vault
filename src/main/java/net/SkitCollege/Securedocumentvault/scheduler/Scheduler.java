package net.SkitCollege.Securedocumentvault.scheduler;

import net.SkitCollege.Securedocumentvault.Entity.DocumentsEntity;
import net.SkitCollege.Securedocumentvault.Repository.SecureDocumentRepository;
import net.SkitCollege.Securedocumentvault.Service.EmailNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class Scheduler {

    @Autowired
private  SecureDocumentRepository documentRepository;
    @Autowired
private  EmailNotificationService emailService;

//    public Scheduler(SecureDocumentRepository documentRepository,
//                                     EmailNotificationService emailService) {
//        this.documentRepository = documentRepository;
//        this.emailService = emailService;
//    }

    @Scheduled(cron="*/30 * * * * ?")
//    public void runaAllOnce()
//    {
//        System.out.println("🔔 Scheduler started");
//        sendUpdateReminders();
//
//        sendExpiryReminders();
//    }
    private void sendUpdateReminders()
    {
        LocalDate cutoffdate= LocalDate.now().minusDays(20);
        List<DocumentsEntity> updatedocs= documentRepository.findByLastUpdatedDateBefore(cutoffdate);

        for(DocumentsEntity doc : updatedocs)
        {
            emailService.sendUpdateReminder(doc);
        }
    }
    private void sendExpiryReminders()
    {
        LocalDate today=LocalDate.now();
        LocalDate reminderDate=today.plusDays(7);
        System.out.println("Checking expiry between " + today + " and " + reminderDate);

        List<DocumentsEntity> expdocs=documentRepository.findByExpiryDateBetween(today,reminderDate);
        System.out.println("Docs found: " + expdocs.size());
        for (DocumentsEntity doc : expdocs) {
            System.out.println("email sent to:"+doc.getOwnerEmail());
            emailService.sendExpiryReminder(doc);
        }
    }

}

