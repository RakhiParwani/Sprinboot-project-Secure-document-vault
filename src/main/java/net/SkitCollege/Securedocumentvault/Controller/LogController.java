package net.SkitCollege.Securedocumentvault.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {

    private static final Logger logger =
            LoggerFactory.getLogger(LogController.class);

    @GetMapping("/users")
    public String getUsers() {

        logger.info("Fetching all users");

        return "Users fetched successfully";
    }
}