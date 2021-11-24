package ba.polozi.demo.services;

import ba.polozi.demo.config.JwtTokenProvider;
import ba.polozi.demo.models.Role;
import ba.polozi.demo.models.User;
import ba.polozi.demo.repositories.UserRepository;
import ba.polozi.demo.requests.LoginRequest;
import ba.polozi.demo.requests.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public User findUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }

    public User getUserByUsername(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }

    public User createUserAccount(RegistrationRequest registrationRequest) {
        Role role = roleService.findRoleByName("ROLE_CANDIDATE");
        User user = new User(registrationRequest.getFirstName(),
                registrationRequest.getLastName(), registrationRequest.getPhoneNumber(),
                registrationRequest.getEmail(), registrationRequest.getUsername(),
                registrationRequest.getPassword(), role);
        String encodedPassword = passwordEncoder.encode(registrationRequest.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    public String signInUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.generateToken(authentication);
    }

    public boolean exist(RegistrationRequest registrationRequest) {
        if (userRepository.existsByEmail(registrationRequest.getEmail())) {
            return true;
        }
        return false;
    }
}