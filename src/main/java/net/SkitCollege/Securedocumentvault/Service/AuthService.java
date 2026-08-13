package net.SkitCollege.Securedocumentvault.Service;

import net.SkitCollege.Securedocumentvault.Entity.UserEntity;
import net.SkitCollege.Securedocumentvault.Repository.UserRepository;
import net.SkitCollege.Securedocumentvault.dto.LoginRequest;
import net.SkitCollege.Securedocumentvault.dto.SignupRequest;
import net.SkitCollege.Securedocumentvault.enums.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {


    private static final Logger log =
            LoggerFactory.getLogger(AuthService.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String signup(SignupRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        UserEntity user = new UserEntity();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        if (request.getEmail().equalsIgnoreCase("admin@gmail.com")) {
            user.setRole(Role.ADMIN);
        } else {
            user.setRole(Role.USER);
        }

        userRepository.save(user);
        log.info("Signup successful for email: {}", request.getEmail());
        return "Signup successful";
    }

    public String login(LoginRequest request) {

        UserEntity user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->{
                    log.error("Login failed: User not found -> {}", request.getEmail());
                    return new IllegalArgumentException("User not found");
                });


        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        return "Login successful";
    }
    public UserEntity getMyDetails(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                {
                    log.error("User details not found for email: {}", email);
                    return new RuntimeException("User not found");
                });
    }

}
