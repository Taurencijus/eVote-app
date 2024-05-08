package javau9.ca.evote.configurations;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import javau9.ca.evote.models.User;
import javau9.ca.evote.models.UserType;
import javau9.ca.evote.repositories.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public void run(String... args) throws Exception {
        createAdminAccount();
    }

    private void createAdminAccount() {
        boolean adminExists = userRepository.existsByType(UserType.ADMIN);
        if (!adminExists) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("password123"));
            admin.setEmail("admin@evote.com");
            admin.setType(UserType.ADMIN);
            userRepository.save(admin);
            System.out.println("No Admin account detected. New Admin account created");
        }
    }
}
