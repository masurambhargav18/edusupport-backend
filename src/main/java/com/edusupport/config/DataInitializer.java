package com.edusupport.config;

import com.edusupport.entity.Role;
import com.edusupport.entity.User;
import com.edusupport.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner seed(
            UserRepository repo,
            PasswordEncoder passwordEncoder
    ) {

        return args -> {

            createUser(
                    repo,
                    passwordEncoder,
                    "Admin User",
                    "admin@edusupport.com",
                    "admin123",
                    Role.ADMIN
            );

            createUser(
                    repo,
                    passwordEncoder,
                    "Staff User",
                    "staff@edusupport.com",
                    "staff123",
                    Role.STAFF
            );

            createUser(
                    repo,
                    passwordEncoder,
                    "Student User",
                    "student@edusupport.com",
                    "student123",
                    Role.STUDENT
            );
        };
    }

    private void createUser(
            UserRepository repo,
            PasswordEncoder passwordEncoder,
            String name,
            String email,
            String password,
            Role role
    ) {

        User user =
                repo.findByEmail(email)
                        .orElse(null);

        if (user == null) {

            repo.save(
                    User.builder()
                            .name(name)
                            .email(email)
                            .password(
                                    passwordEncoder.encode(password)
                            )
                            .role(role)
                            .build()
            );

            return;
        }

        String currentPassword =
                user.getPassword();

        if (currentPassword == null ||
                !currentPassword.startsWith("$2a$")) {

            user.setPassword(
                    passwordEncoder.encode(password)
            );

            repo.save(user);
        }
    }
}