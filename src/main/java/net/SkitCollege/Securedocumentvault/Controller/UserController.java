package net.SkitCollege.Securedocumentvault.Controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.SkitCollege.Securedocumentvault.Service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Tag(name = "User API's")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(Authentication authentication) {
        return ResponseEntity.ok(
                userService.getProfile(authentication.getName())
        );
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(
            Authentication authentication,
            @RequestBody Object updateRequest) {
        return ResponseEntity.ok(
                userService.updateProfile(authentication.getName(), updateRequest)
        );
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAccount(Authentication authentication) {
        userService.deleteUser(authentication.getName());
        return ResponseEntity.ok("Account deleted successfully");
    }
}
