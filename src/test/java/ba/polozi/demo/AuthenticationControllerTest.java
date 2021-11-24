package ba.polozi.demo;

import ba.polozi.demo.config.JwtTokenProvider;
import ba.polozi.demo.controllers.AuthenticationController;
import ba.polozi.demo.models.Candidate;
import ba.polozi.demo.models.Instructor;
import ba.polozi.demo.models.Role;
import ba.polozi.demo.models.User;
import ba.polozi.demo.repositories.CandidateRepository;
import ba.polozi.demo.repositories.InstructorRepository;
import ba.polozi.demo.repositories.UserRepository;
import ba.polozi.demo.requests.CandidateRequest;
import ba.polozi.demo.requests.InstructorRequest;
import ba.polozi.demo.requests.LoginRequest;
import ba.polozi.demo.requests.RegistrationRequest;
import ba.polozi.demo.services.AuthService;
import ba.polozi.demo.services.CandidateService;
import ba.polozi.demo.services.InstructorService;
import ba.polozi.demo.services.RoleService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AuthenticationControllerTest {
    @Autowired
    private AuthenticationController authenticationController;

    @Test
    @Order(1)
    public void shouldRegisterUser() {
        RegistrationRequest registrationRequest = new RegistrationRequest("Test", "User", "062453953", "asehic2",
                "test@example123.com", "password");
        ResponseEntity<?> responseEntity = authenticationController.register(registrationRequest);
        assertEquals(201, responseEntity.getStatusCodeValue());
    }

    @Test
    public void shouldLoginUser() {
        LoginRequest loginRequest = new LoginRequest("test@example123.com", "password");
        ResponseEntity<?> responseEntity = authenticationController.login(loginRequest);
        System.out.println(responseEntity.getBody());
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void shouldFailLoginWithIncorrectPassword() {
        LoginRequest loginRequest = new LoginRequest("test@example123.com", "password123");
        ResponseEntity<?> responseEntity = authenticationController.login(loginRequest);
        assertEquals(400, responseEntity.getStatusCodeValue());
    }

    @Test
    public void shouldFailLoginWithIncorrectEmail() {
        LoginRequest loginRequest = new LoginRequest("non-existant-email@example.com", "some-password");
        ResponseEntity<?> responseEntity = authenticationController.login(loginRequest);
        assertEquals(400, responseEntity.getStatusCodeValue());
    }

}












