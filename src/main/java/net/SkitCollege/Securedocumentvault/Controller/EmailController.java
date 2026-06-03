package net.SkitCollege.Securedocumentvault.Controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import net.SkitCollege.Securedocumentvault.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
@Tag(name="Email API's")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/send")
    public String sendMail(Authentication authentication) {

        // Logged-in user's email
        String userEmail = authentication.getName();

        emailService.sendEmail(
                userEmail,
                "Secure Document Vault Notification",
                "Hello! You are successfully using Secure Document Vault."
        );

        return "Email sent successfully to " + userEmail;
    }
}