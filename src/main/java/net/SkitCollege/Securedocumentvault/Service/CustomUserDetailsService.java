package net.SkitCollege.Securedocumentvault.Service;

import net.SkitCollege.Securedocumentvault.Entity.UserEntity;
import net.SkitCollege.Securedocumentvault.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
//iska kaam sirf user ko load krna hota h datbase se bs,make userdetails obj for spring security understanding and return to spring securitynot user entity

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));

            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getEmail())
                    .password(user.getPassword())
                    .roles(user.getRole().name())//admin->"admin"->role_admin/role_user
                    .build();
        }

    }



