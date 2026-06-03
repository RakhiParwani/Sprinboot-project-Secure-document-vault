package net.SkitCollege.Securedocumentvault.Controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.SkitCollege.Securedocumentvault.Service.AuthService;
import net.SkitCollege.Securedocumentvault.dto.LoginRequest;
import net.SkitCollege.Securedocumentvault.dto.SignupRequest;
import net.SkitCollege.Securedocumentvault.utils.Jwtutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name="Auth API's")
public class AuthController {

    @Autowired
    private AuthService securedocumentService;

    @Autowired
    AuthenticationManager authenticationmanager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private Jwtutil jwtutil;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
        try {
            return ResponseEntity.ok(securedocumentService.signup(request));
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        try {
            authenticationmanager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            UserDetails userDetails =
                    userDetailsService.loadUserByUsername(request.getEmail());

            String jwt = jwtutil.generateToken(userDetails.getUsername());

            return new ResponseEntity<>(jwt, HttpStatus.OK);

        } catch (Exception e) {
            log.error("Exception occured", e);
            return new ResponseEntity<>(
                    "Incorrect username or password",
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}