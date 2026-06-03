package net.SkitCollege.Securedocumentvault.Controller;

import net.SkitCollege.Securedocumentvault.Service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PutMapping("/profile/{id}")
    public String updateProfile(
            @PathVariable Long id,
            @RequestBody Map<String, String> data
    ) {
        return profileService.updateProfile(
                id,
                data.get("name"),
                data.get("email")
        );
    }
}