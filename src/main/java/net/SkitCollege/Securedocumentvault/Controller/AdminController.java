package net.SkitCollege.Securedocumentvault.Controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import net.SkitCollege.Securedocumentvault.Entity.DocumentsEntity;
import net.SkitCollege.Securedocumentvault.Entity.UserEntity;
import net.SkitCollege.Securedocumentvault.Repository.UserRepository;
import net.SkitCollege.Securedocumentvault.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Tag(name="Admin API's")
public class AdminController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdminService adminService;
    @GetMapping("/dashboard")
    public ResponseEntity<?> adminDetails(Authentication authentication) {

        return ResponseEntity.ok(
                "Admin logged in: " + authentication.getName()
        );
    }
    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers() {

        List<UserEntity> users =adminService.getAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body("No users found");
        }

        return ResponseEntity
                .ok(users);
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {

        boolean deleted = adminService.deleteUser(id);

        if(!deleted) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("User not found");
        }

        return ResponseEntity.ok("User deleted successfully");
    }

}
