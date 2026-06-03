package net.SkitCollege.Securedocumentvault;

import net.SkitCollege.Securedocumentvault.Filter.Jwtfilter;
import net.SkitCollege.Securedocumentvault.Service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class Securityconfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;
@Autowired
private Jwtfilter jwtfilter;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                // ✔️ csrf off
                .cors()   // 🔥 ADD THIS (IMPORTANT FIX)
                .and()
                .authorizeRequests()
                .antMatchers("/auth/signup", "/auth/login").permitAll()
                .antMatchers("/test-lru").permitAll()
                .antMatchers("/users").permitAll()
                .antMatchers("/send-email").permitAll()
                .antMatchers("/documents/{docId}/token").permitAll()
                .antMatchers("/Secure/loginn").permitAll()
                .antMatchers(
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/v3/api-docs/**",
                        "/api-docs/**"
                ).permitAll()
                .antMatchers("/documents/view").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/admin/**").authenticated()
                .antMatchers("/documents", "/documents/**").permitAll()
                .antMatchers("/api/signup", "/api/login").permitAll()
                .anyRequest().authenticated()
                .and()
//                .oauth2Login()
//                .and()
                .addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
