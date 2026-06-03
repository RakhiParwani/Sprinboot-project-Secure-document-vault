package net.SkitCollege.Securedocumentvault.Service;

import lombok.RequiredArgsConstructor;
import net.SkitCollege.Securedocumentvault.Entity.UserEntity;
import net.SkitCollege.Securedocumentvault.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity getProfile(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserEntity updateProfile(String email, Object updateRequest) {
        UserEntity user = getProfile(email);

        // Update fields manually as per your DTO
        return userRepository.save(user);
    }

    public void deleteUser(String email) {
        UserEntity user = getProfile(email);
        userRepository.delete(user);
    }
}
