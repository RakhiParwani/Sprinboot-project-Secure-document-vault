package net.SkitCollege.Securedocumentvault.service;

import net.SkitCollege.Securedocumentvault.Service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;
    @Test
    public void testmail() {
        emailService.sendEmail("parwanidinesh79@gmail.com", "testing", "heloo aap kese ho");
    }

}
